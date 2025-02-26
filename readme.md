# 📌 EncryptOperation

## 🔐 EncryptOperation.encrypt(String text, String password)
- **`text`** → Texto a encriptar.  
- **`password`** → Contraseña para encriptarlo (debe tener **16, 24 o 32 caracteres**).  

## 💾 EncryptOperation.saveToFile(String nombredelfichero, String data)
- **`nombredelfichero`** → Nombre del fichero donde se guardarán los datos.  
- **`data`** → String codificado en **Base64** que contiene el texto cifrado en **AES**.  

---

# 🔓 DecryptOperation

## 🔑 DecryptOperation.decrypt(String password)
- **`password`** → Contraseña a introducir para desencriptar el texto (**sin cifrar**).  
🔹 **Descripción**: Lee el fichero `costesOperacion.txt` como un String y llama a la función `decryptText`.  

## 📜 DecryptOperation.decryptText(String encryptedText, String password)
- **`encryptedText`** → Texto codificado en **Base64**, cuyo contenido es un **String cifrado en AES**, previamente generado con `EncryptOperation.encrypt`.  

### ❓ ¿Para qué sirve?
✔️ Encriptar **Strings**, ya sea una operación u otro tipo de datos.  
