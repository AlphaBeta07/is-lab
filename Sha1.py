import hmac
import hashlib

key = input("Enter secret key: ").encode()     
message = input("Enter message: ").encode()   

hmac_result = hmac.new(key, message, hashlib.sha1)
print("HMAC (SHA-1):", hmac_result.hexdigest())
