# HmacSignage
Generate Hmac for Object Using Annotations for Field or Classes

###Explanation:
This helps to generate Hmac for an object  by adding the annotation '@MacSigned' on class level or or some fields within that class, uses Hmac256 algorithm.

##Example
 
**Annotate class to signed**
		
		
		@MacSigned
		public class NotificationClassLevel {
		  private Long id;
		  private String type;
		  private String name;
		}
		
		OR

		public class NotificationFieldLevel {
		  @MacSigned
		  private Long id;

		  @MacSigned
		  private String type;

		  @MacSigned
		  private String name;
		}
		
**Instantiate ObjectHmacSigner utility class**

    ObjectHmacSigner signer = new ObjectHmacSigner();

    NotificationClassLevel notifyObject = new NotificationClassLevel(1L, "sd","af");
	String testKey = "38D2ADECE6203DF2DABFA68D87C55CAE0BC030876120E0505B0C775C88323D0F";
	// call generateMac method with parameters [HMAC key, delimeter, object]
    s.generateMac(testKey, ":", notifyObject);
    
    

    
		
