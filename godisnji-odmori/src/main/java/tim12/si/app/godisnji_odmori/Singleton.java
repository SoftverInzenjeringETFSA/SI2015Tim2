package tim12.si.app.godisnji_odmori;

public class Singleton {
		   private static Singleton instance = null;
		   private String username;
		   
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		protected Singleton() {
		      // Exists only to defeat instantiation.
		   }
		   public static Singleton getInstance() {
		      if(instance == null) {
		         instance = new Singleton();
		      }
		      return instance;
		   }
		   
		   protected void metoda()
		   {
			   System.out.println("Nesto");
		   }
}
