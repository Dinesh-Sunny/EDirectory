package in.teachcoder.app.edirectory;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class User implements Parcelable {

	private long id;
	private String name;
	private String designation;
	private String department;
	private String Email_id;
	private String location;
	private String mobile;
	private String image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail_id() {
		return Email_id;
	}

	public void setEmail_id(String email_id) {
		Email_id = email_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return name + '\n' + department ;
	}

	public User() {
	}

	public User(Parcel in) {
		Log.i(MainActivity.LOGTAG, "Parcel constructor");

		id = in.readLong();
		name = in.readString();
		department = in.readString();
		designation = in.readString();
		location = in.readString();
		mobile = in.readString();
		Email_id = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.i(MainActivity.LOGTAG, "writeToParcel");

		dest.writeLong(id);
		dest.writeString(name);
		dest.writeString(department);
		dest.writeString(designation);
		dest.writeString(location);
		dest.writeString(mobile);
		dest.writeString(Email_id);
	}

	public static final Parcelable.Creator<User> CREATOR =
			new Parcelable.Creator<User>() {

				@Override
				public User createFromParcel(Parcel source) {
					Log.i(MainActivity.LOGTAG, "createFromParcel");
					return new User(source);
				}

				@Override
				public User[] newArray(int size) {
					Log.i(MainActivity.LOGTAG, "newArray");
					return new User[size];
				}

			};
}
