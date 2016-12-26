package com.amacodecode.annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import com.amacodecode.annotation.sample.NotificationClassLevel;
import com.amacodecode.annotation.sample.NotificationFieldLevel;

public class ObjectHmacSigner implements HmacSigner {

	@Override
	public String generateMac(String macKey, String optionDelimeter, Object object) throws Exception {
		String payloadToSign = null;

		if (checkIfAnnotationIsOnClass(object)) {
			payloadToSign = getValueOnAnnotatedFields(object, optionDelimeter, true);
		} else if (checkIfAnnotationIsOnMethod(object)){
			payloadToSign = getValueOnAnnotatedFields(object, optionDelimeter, false);
		} else {
			throw new IllegalAccessError("No annotation @MacSigned found on object");
		}
		// handle 
		if (!payloadToSign.isEmpty()) {
			return generate(macKey, payloadToSign);
		} else {
			throw new NullPointerException("No payload String returned!, You have empty object!");
		}		
	}

	private String generate(String keyHex, String payloadToSign) throws Exception {

		Base64 base64 = new Base64();
		// decode HEX Key into bytes
		byte[] keyBytes = Hex.decodeHex(keyHex.toCharArray());
		// compile the payload to sign
		String payloadString = payloadToSign.toString();
		// get payload in bytes
		byte[] payload = payloadToSign.getBytes("UTF-8");
		// instantiate the MAC object using HMAC / SHA256
		Mac hmacSha256 = javax.crypto.Mac.getInstance("HmacSHA256");
		// create a key object using the secret key and MAC object
		SecretKey secretKey = new SecretKeySpec(keyBytes, hmacSha256.getAlgorithm());
		// initialise the MAC object
		hmacSha256.init(secretKey);
		// finalise the MAC operation
		byte[] signedPayload = hmacSha256.doFinal(payload);
		// encode the signed payload in Base64
		byte[] encodedSignedPayload = base64.encode(signedPayload);
		String generatedString = encodedSignedPayload.length != 0 ? new String(encodedSignedPayload, "ASCII") : null;
		return generatedString;
	}

	private boolean checkIfAnnotationIsOnClass(Object object) {
		return object.getClass().isAnnotationPresent(MacSigned.class);
	}
	
	private boolean checkIfAnnotationIsOnMethod(Object object) {
		Stream<Field> stream = Arrays.stream(object.getClass().getDeclaredFields());
		Field field = stream.filter(source -> source.isAnnotationPresent(MacSigned.class) == true)					
		.findFirst()
		.orElse(null);
        
		return field != null;
	}

	private String getValueOnAnnotatedFields(Object foo, String delimeter, boolean isAllFields)
			throws IllegalArgumentException, IllegalAccessException {
		StringBuilder strBuilder = new StringBuilder();
		for (Field f : foo.getClass().getDeclaredFields()) {
			if (isAllFields || f.isAnnotationPresent(MacSigned.class)) {
				f.setAccessible(true);
				strBuilder.append(f.get(foo).toString());
				strBuilder.append(delimeter);
			}
		}
		return strBuilder.toString().substring(0, strBuilder.toString().length() - 1);
	}
}
