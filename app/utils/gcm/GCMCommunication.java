package utils.gcm;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import play.Logger;

public class GCMCommunication {

	private String gcmAuthKey = "AIzaSyBPHmXXZyDaTgcRU5UMXz6vjmBwJkh4CWQ";
	private String regid = "APA91bEjm31zpuqytSm545jruSptqmSa4ykIgC-seFkrHYqSTRuczGVWENLeo8Yq65Sw7V9zldyo6PODgbcNqUG7URU-1Q7NPQG-fwMn42ATS-R5VLzvnMH_mGCmcLhPS-S249DSKrAdsxQoHhSyf3MoECAnI2Z1LgNcYUTHMjMZugE6zHHOBvg";

	public void createFriendsEvent() {
		try {
			String REQUEST_URL = "https://android.googleapis.com/gcm/send";

			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(REQUEST_URL);

			httpPost.setHeader("Authorization", "key=" + gcmAuthKey);
			httpPost.setHeader("Content-Type", "application/json");

			StringEntity params = new StringEntity("{ \"data\": {\"type\": \""
					+ 0 + "\",\"appId\": \"" + 12 + "\",\"lon\": \"" + 12
					+ "\",\"lat\": \"" + 12 + "\"}"
					+ ",\"delay_while_idle\" : false,"
					+ "\"registration_ids\": [\"" + regid + "\"]}");

			httpPost.setEntity(params);

			Logger.info("create friends event response :"
					+ httpclient.execute(httpPost).getStatusLine()
							.getStatusCode());

		} catch (Exception e) {
			Logger.error(e.toString());
		}

	}

}