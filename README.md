E-commerce website made with SpringBoot Java for back-end stuff. Utilized TailwindCSS for front-end stuff.

This is just a demo website, no actual transaction will be made here. However, be careful still with your important information/s, technically - anyone can access your mysql database if not protected.

Current Features:
- Products are fetched from MySQL Database (Price, ItemName, ItemDesc, etc...)
- Using Luhn Algorithm for credit card validation.
- Utilized Facade design pattern for database structure. (I don't know how I made it work, but it just works so...)
- Products are both Abstract Factory design pattern.
- Order system also features emailing upon finalizing transaction. Luhn Algorithm will check if given card is valid, otherwise system will ask for the correct one if invalid (Duh?). Email will be sent to whomever address the user gave (also, Duh?).

To run:
(VSCode)
1. Needs Spring extension.
2. Import the project.
3. Run "npm run -watch" (to build TailwindCSS)
4. Run XAMPP.
5. Start APACHE & MySQL.
6. Start the website through VSCODE Spring Dashboard.
7. localhost:8080/index 
