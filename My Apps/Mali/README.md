This is a Kotlin Multiplatform project targeting Android, iOS using Compose Multiplatform for the UI.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

# 🏠 **MaliApp – Rent Management System**

MaliApp is a mobile-based **rent management system** designed to streamline rental property operations. It enables **landlords, tenants, and property managers** to efficiently manage payments, track rent status, and receive notifications—all in one place.

---

## 📌 **Features**
### 🔹 **For Tenants**
✔️ View rent history and payment status  
✔️ Make **Mpesa payments** directly from the app  
✔️ Receive **automated rent reminders** via push notifications  
✔️ Access digital rent receipts

### 🔹 **For Landlords & Property Managers**
✔️ Add, edit, and manage **tenant records**  
✔️ Track **monthly payments & overdue balances**  
✔️ Generate **rent reports** in PDF format  
✔️ Securely **store payment transactions**

---

## 🛠 **Tech Stack**
| Component           | Technology Used |
|---------------------|----------------|
| **Frontend**       | Kotlin, Jetpack Compose |
| **Backend**        | Firebase Firestore, Firebase Authentication |
| **Payments**       | Mpesa API (STK Push) |
| **Notifications**  | Firebase Cloud Messaging (FCM) |
| **Offline Support**| Room Database |
| **PDF Generation** | Android PDFDocument API |

---

## 📲 **Installation & Setup**
### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/yourusername/maliapp.git
cd maliapp
```
### **2️⃣ Open in Android Studio**
- Ensure you have **Android Studio** installed.
- Open the project and allow Gradle to sync.

### **3️⃣ Configure Firebase**
- Set up a Firebase project
- Add the **google-services.json** file to your app’s `app` directory.

### **4️⃣ Set Up Mpesa API**
- Register on **Daraja API** for Mpesa STK Push
- Configure API keys in `MpesaConfig.kt`

### **5️⃣ Run the App**
- Connect an **Android device/emulator**
- Click **Run** ▶️

---

## ⚡ **How It Works**
### ✅ **Tenant Payment Flow**
1. Log in via email authentication
2. Go to **Rent Payment**
3. Enter the amount and **confirm Mpesa payment**
4. Receive an **SMS confirmation & in-app receipt**

### ✅ **Landlord Dashboard**
1. View **all tenant records**
2. Track **paid, pending, and overdue** payments
3. Generate **monthly payment reports (PDF)**
4. Send **reminders to tenants**

---

## 🚀 **Challenges & Future Improvements**
### **Challenges**
🔹 Handling **real-time payments & failures**  
🔹 Implementing **secure user authentication**

### **Future Improvements**
✅ Support for **multiple properties**  
✅ AI-powered **rent prediction & analytics**  
✅ Integration with **other payment methods (Card, PayPal)**

---

## 🤝 **Contributing**
Want to improve MaliApp? Feel free to:
- **Fork** the repo
- **Create** a pull request
- **Report bugs & suggest features** in Issues

---

## 📜 **License**
This project is **open-source** under the **MIT License**.

---

🚀 **MaliApp – Simplifying Rent Payments, One Transaction at a Time!**