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
# 👤 UserSystem

## 📝 UserSystem.register(String username, String password, String key)
- **`username`** → Nombre de usuario a registrar.  
- **`password`** → Contraseña que se almacenará cifrada.  
- **`key`** → Clave de cifrado utilizada para proteger la contraseña.  
🔹 **Descripción**: Registra un usuario en el sistema y almacena sus credenciales en `UserDb.txt`.  

## 🔑 UserSystem.login(String username, String password, String key)
- **`username`** → Nombre de usuario que intenta iniciar sesión.  
- **`password`** → Contraseña que se comparará con la almacenada en la base de datos.  
- **`key`** → Clave de cifrado utilizada para descifrar la contraseña.  
🔹 **Descripción**: Verifica las credenciales ingresadas comparándolas con las almacenadas.  

## 🔍 UserSystem.userExists(String username)
- **`username`** → Nombre del usuario a verificar.  
🔹 **Descripción**: Comprueba si un usuario ya está registrado en `UserDb.txt`.  

## 💾 UserSystem.saveToFile(String filename, String data)
- **`filename`** → Nombre del archivo donde se guardarán los datos.  
- **`data`** → Información del usuario cifrada que se añadirá al archivo.  
🔹 **Descripción**: Agrega la información del usuario al archivo `UserDb.txt` sin sobrescribir los datos existentes.  

## 🏁 UserSystem.main(String[] args)
🔹 **Descripción**:  
✔️ Permite registrar e iniciar sesión con un usuario desde la consola.  
✔️ Solicita la clave de cifrado para encriptar y desencriptar contraseñas.  

### ❓ ¿Para qué sirve?
✔️ Gestionar usuarios con contraseñas cifradas.  
✔️ Permitir el registro y autenticación segura de usuarios.  
✔️ Almacenar datos de forma persistente en `UserDb.txt`.  
