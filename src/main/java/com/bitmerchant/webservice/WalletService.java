package com.bitmerchant.webservice;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Map;
import java.util.NoSuchElementException;

import com.bitmerchant.db.Actions;
import com.bitmerchant.tools.DataSources;
import com.bitmerchant.tools.Tools;
import com.bitmerchant.wallet.LocalWallet;

import static com.bitmerchant.db.Tables.MerchantInfoView;

public class WalletService {

	// How long to keep the cookies
	public static final Integer COOKIE_EXPIRE_SECONDS = Tools.cookieExpiration(1440);

	public static void setup() {

		get("/wallet/ssl", (req, res) -> {

			return LocalWallet.INSTANCE.controller.getIsSSLEncrypted();
		});

		post("/wallet/power_off", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);

				LocalWallet.INSTANCE.stop();
				return "A yellow brick road";

			} catch (Exception e) {
				res.status(666);
				return e.getMessage();
			}

		});

		post("/wallet/restart", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);

				LocalWallet.INSTANCE.restart();
				return "A yellow brick road ";

			} catch (Exception e) {
				res.status(666);
				return e.getMessage();
			}

		});


		get("/", (req, res) -> {
			res.redirect("wallet");
			return "a yellow brick road";
		});

		get("/wallet/web_service_url", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			//			return lw.controller.getStatusProgress();
			return DataSources.WEB_SERVICE_EXTERNAL_URL();

		});


		get("/wallet/status_progress", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);
				//			return lw.controller.getStatusProgress();
				return LocalWallet.INSTANCE.controller.getStatusProgress();
			} catch (Exception e) {
				res.status(666);
				return e.getMessage();
			}

		});

		get("/wallet/status_text", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);
				return LocalWallet.INSTANCE.controller.getStatusText();
			} catch (Exception e) {
				res.status(666);
				return e.getMessage();
			}
		});

		get("/wallet/receive_address", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			return LocalWallet.INSTANCE.controller.getAddressText();
		});

		get("/wallet/balance", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			return LocalWallet.INSTANCE.controller.getBalanceText();
		});

		get("/wallet/native_balance", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			Tools.dbInit();
			String balance = LocalWallet.INSTANCE.controller.getNativeBalance();
			Tools.dbClose();
			return balance;
		});

		get("/wallet/wallet_words", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			return LocalWallet.INSTANCE.controller.getWalletWords();
		});

		get("/wallet/wallet_creation_date", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			return LocalWallet.INSTANCE.controller.getWalletCreationDateStr();
		});

		get("/wallet/wallet_is_encrypted", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			return LocalWallet.INSTANCE.controller.getWalletIsEncrypted();
		});
		get("/wallet/wallet_is_locked", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			return LocalWallet.INSTANCE.controller.getWalletIsLocked();
		});

		post("/wallet/set_wallet_password", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			String password = Tools.createMapFromAjaxPost(req.body()).get("password");
			String message = LocalWallet.INSTANCE.controller.setWalletPassword(password);
			return message;
		});

		post("/wallet/remove_wallet_password", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);
				String password = Tools.createMapFromAjaxPost(req.body()).get("password");
				String message = LocalWallet.INSTANCE.controller.removeWalletPassword(password);

				return message;

			} catch (NoSuchElementException e) {
				res.status(666);
				return e.getMessage();
			}
		});

		post("/wallet/unlock_wallet", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);
				String password = Tools.createMapFromAjaxPost(req.body()).get("password");
				String message = LocalWallet.INSTANCE.controller.unlockWallet(password);

				return message;

			} catch (NoSuchElementException e) {
				res.status(666);
				return e.getMessage();
			}

		});

		post("/wallet/restore_wallet", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);

				Map<String, String> formItems = Tools.createMapFromAjaxPost(req.body());

				String walletWords = formItems.get("wallet_words");
				String dateStr = formItems.get("wallet_creation_date");

				String message = LocalWallet.INSTANCE.controller.restoreWallet(walletWords, dateStr);

				return message;

			} catch (NoSuchElementException e) {
				res.status(666);
				return e.getMessage();
			}

		});

		post("/wallet/send_money", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);

				Map<String, String> formItems = Tools.createMapFromAjaxPost(req.body());

				String amount = formItems.get("sendAmount");
				String toAddress = formItems.get("address");

				String message = LocalWallet.INSTANCE.controller.sendMoney(amount, toAddress);

				return message;

			} catch (NoSuchElementException e) {
				res.status(666);
				return e.getMessage();
			}

		});


		post("/wallet/send_money_encrypted", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);

				Map<String, String> formItems = Tools.createMapFromAjaxPost(req.body());

				String amount = formItems.get("sendAmount");
				String toAddress = formItems.get("address");
				String password = formItems.get("password");

				LocalWallet.INSTANCE.controller.unlockWallet(password);
				String message = LocalWallet.INSTANCE.controller.sendMoney(amount, toAddress);

				return message;

			} catch (NoSuchElementException e) {
				res.status(666);
				return e.getMessage();
			}

		});

		get("/wallet/send_status", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			return LocalWallet.INSTANCE.controller.getSendStatus();
		});

		get("/wallet/get_transactions", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			Tools.dbInit();
			String txs = LocalWallet.INSTANCE.controller.getTransactionsJSON();
			Tools.dbClose();
			return txs;

		});

		get("/wallet/newest_received_tx", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);
				// use a cookie, not a return
				//			res.cookie("newestReceivedTransaction", LocalWallet.instance.controller.getNewestReceivedTransaction(),
				//					COOKIE_EXPIRE_SECONDS, true);
				res.cookie("newestReceivedTransaction", LocalWallet.INSTANCE.controller.getNewestReceivedTransactionHash(),
						COOKIE_EXPIRE_SECONDS);
				return LocalWallet.INSTANCE.controller.getNewestReceivedTransaction();
			} catch (NoSuchElementException e) {
				res.status(666);
				return e.getMessage();
			}
		});


		get("/wallet/merchant_info", (req, res) -> {
			Tools.allowOnlyLocalHeaders(req, res);
			Tools.dbInit();
			MerchantInfoView mi = MerchantInfoView.findById(1);
			Tools.dbClose();

			return mi.toJson(false);
		});

		post("/wallet/save_merchant_info", (req, res) -> {
			try {
				Tools.allowOnlyLocalHeaders(req, res);
				Tools.dbInit();
				Map<String, String> formItems = Tools.createMapFromAjaxPost(req.body());

				String name = formItems.get("name");
				String currIso = formItems.get("price_currency_iso");

				String message = Actions.saveMerchantInfo(name, currIso);
				Tools.dbClose();
				return message;

			} catch (NoSuchElementException e) {
				res.status(666);
				return e.getMessage();
			}

		});

	}
}
