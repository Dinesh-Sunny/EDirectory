package in.teachcoder.app.edirectory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.util.Log;



public class UsersPullParser {

	private static final String LOGTAG = "PullParserActivity";
	
	private static final String NAME = "NAME";
	private static final String DESIGNATION = "DESIGNATION";
	private static final String DEPARTMENT = "DEPARTMENT";
	private static final String LOCATION = "LOCATION";
	private static final String MOBILE = "MOBILE";
	private static final String EMAIL_ID = "EMAIL_ID";
//	private static final String USER_IMAGE = "IMAGE";
	
	private User currentUser  = null;
	private String currentTag = null;
	List<User> users = new ArrayList<User>();

	public List<User> parseXML(Context context) {

		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			
			InputStream stream = context.getResources().openRawResource(R.raw.directory);
			xpp.setInput(stream, null);

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					handleStartTag(xpp.getName());
				} else if (eventType == XmlPullParser.END_TAG) {
					currentTag = null;
				} else if (eventType == XmlPullParser.TEXT) {
					handleText(xpp.getText());
				}
				eventType = xpp.next();
			}

		} catch (NotFoundException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (XmlPullParserException e) {
			Log.d(LOGTAG, e.getMessage());
		} catch (IOException e) {
			Log.d(LOGTAG, e.getMessage());
		}

		return users;
	}

	private void handleText(String text) {
		String xmlText = text;
		if (currentUser != null && currentTag != null) {
			 if (currentTag.equals(NAME)) {
				currentUser.setName(xmlText);
			}
			else if (currentTag.equals(DESIGNATION)) {
				currentUser.setDesignation(xmlText);
			}
			else if (currentTag.equals(DEPARTMENT)) {
				currentUser.setDepartment(xmlText);
			}
			 else if (currentTag.equals(LOCATION)) {
				 currentUser.setLocation(xmlText);
			 }
			 else if (currentTag.equals(MOBILE)) {

				 currentUser.setMobile(xmlText);
			 }
			else if (currentTag.equals(EMAIL_ID)) {
				 currentUser.setEmail_id(xmlText);
			}
		}
	}

	private void handleStartTag(String name) {
		if (name.equals("row")) {
			currentUser = new User();
			users.add(currentUser);
		}
		else {
			currentTag = name;
		}
	}
}
