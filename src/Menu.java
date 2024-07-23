
public class Menu {

    public static void loginMenu(){
        System.out.println("#....... Please Login to Continue ........#");
        System.out.println("# Options:");
        System.out.println("#\t0: Exit System");
        System.out.println("#\t1: Show Menu Options");
        System.out.println("#\t2: login");
        System.out.println("#\t3: About Us");
        System.out.print("# Choose Option #: ");
    }

    public static void studentMenu(){
        System.out.println("########## Welcome  Student ########");
        System.out.println("# Options:");
        System.out.println("#\t0: Logout");
        System.out.println("#\t1: Check Your Status");
        System.out.println("#\t2: Change Password");
        System.out.println("#\t3: Give us your feedback");
        System.out.print("# Choose Option #: ");
    }

    public static void teacherMenu(){
        System.out.println("########## Welcome  Teacher ########");
        System.out.println("\tOptions:");
        System.out.println("\t0: Logout");
        System.out.println("\t1: Check Your Information");
        System.out.println("\t2: Change Password");
        System.out.println("\t3: Add Student Score");
        System.out.println("\t4: See Classrooms");
        System.out.println("\t5: See Sessions of the weak");
        System.out.println("\t6: Manage Classrooms");

        System.out.print("Choose Option #: ");
    }

    public static void adminMenu(){
        System.out.println("########## Welcome  Admin ########");
        System.out.println("#\tOptions:");
        System.out.println("#\t0: Logout");
        System.out.println("#\t1: Display Your Information");
        System.out.println("#\t2: Add Student");
        System.out.println("#\t3: Add Teacher");
        System.out.println("#\t4: Add Classroom");
        System.out.println("#\t5: Add Staff");
        System.out.println("#\t6: Display Users");
        System.out.println("#\t7: Display Students");
        System.out.println("#\t8: Display Teachers");
        System.out.println("#\t9: Display classrooms");
        System.out.print("Choose Option #: ");
    }

    public static void familyMenu(){
        System.out.println("########## Welcome  Family ########");
        System.out.println("\tOptions:");
        System.out.println("\t0: Logout");
        System.out.println("\t1: Check Students status");
        System.out.println("\t2: Send feedback");
        System.out.print("Choose Option #: ");
    }

    public static void showDeveloperInfo(){

        String description = "This system is developed for the course Object Oriented Programming using Java";
        System.out.println("\n##################### Welcome #####################");
        System.out.println(description);
        System.out.println("Members or the group are:\n");
        
        System.out.println("\t1. Mengistu Getie");
        System.out.println("\t2. Kirubel Ambaye");
        System.out.println("\t3. Hafiz Temam");
        System.out.println("\t4. Hailemichael Dagne");
        System.out.println("\t5. Seid Issa");
        System.out.println("\t6. Muhammed Seyid");
        System.out.println("##################### Welcome #####################\n");
    }
}
