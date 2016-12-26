
import org.apache.commons.codec.DecoderException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amacodecode.annotation.ObjectHmacSigner;
import com.amacodecode.annotation.sample.NotificationClassLevel;
import com.amacodecode.annotation.sample.NotificationFieldLevel;

public class HmacObjectSignerTest {
	private static NotificationClassLevel classLevel;
	private static NotificationFieldLevel fieldLevel;
	private static ObjectHmacSigner signer;
	private static String testHmacKey;
	
	@BeforeClass
    public static void initObjects() {
		classLevel = new NotificationClassLevel(1L, "AmaCodeCode", "Delusional");
		fieldLevel = new NotificationFieldLevel(1L, "FieldLevel", "Faked");
		signer = new ObjectHmacSigner();
		testHmacKey = "38D2ADECE6203DF2DABFA68D87C55CAE0BC030876120E0505B0C775C88323D0F";
	}

	@Test(expected = IllegalAccessError.class)
	public void hmacMustReturnIllegalAccessErrorIfObjectDoesntHaveAnnotation() throws Exception {
		Object emptyObject = new Object();
		signer.generateMac("", ":", emptyObject);
	}
	
	@Test
	public void hmacMustReturnValuesIfAnnotationIsOnClass() throws Exception {
		String generatedSignature = signer.generateMac(testHmacKey, ":", classLevel);
		Assert.assertEquals(generatedSignature, "XWt7oBx+UMVviVlfo8bG8+ZUF1T5BjlRCnFNysecMnU=");
	}

	@Test
	public void hmacMustReturnValuesIfAnnotationIsOnMethod() throws Exception {
		String generatedSignature = signer.generateMac(testHmacKey, ":", fieldLevel);
		Assert.assertEquals(generatedSignature, "R+hJHP7BPOtdTkCcnbvPIo/EZpYfYSxwNWXejNIIJfg=");
	}
	
	@Test(expected = DecoderException.class)
	public void hmacMustThrowDecoderExceptionWhenInvalidKey() throws Exception {
		signer.generateMac("QERYT", ":", fieldLevel);
	}
	
}
