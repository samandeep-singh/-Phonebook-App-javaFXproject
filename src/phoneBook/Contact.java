package phoneBook;

public class Contact {
	 
		private String name;
		private long phone;
		private String email;
		
		//empty constructor
		public Contact() {
			this("", 0, "");
		}
		public Contact(String name, long phone, String email) {
			
			this.name = name;
			this.phone = phone;
			this.email = email;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public long getPhone() {
			return phone;
		}
		public void setPhone(int phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public String toString() {
			
			return (  "Name: " + name + " " + "Phone no: " + phone + " " + "Email: " + email);
		}
}
