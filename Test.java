public class Test {
    public static void main(String[] args) {

        record User(String name, int age) {}

        User u = new User("Bro", 25);

        String result = switch (u.age()) {
            case 25 -> "Perfect age";
            default -> "Other";
        };

        System.out.println(result);
    }
}
