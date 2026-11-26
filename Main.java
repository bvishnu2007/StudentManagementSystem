import java.util.*;

class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String category;
    private int availableCopies;

    public Book(int bookId, String bookName, String author, String category, int availableCopies) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.availableCopies = availableCopies;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        if (availableCopies >= 0) {
            this.availableCopies = availableCopies;
        }
    }

    public void displayBook() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Book Name: " + bookName);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Available Copies: " + availableCopies);
    }
}

class LibraryMember {
    private int memberId;
    private String memberName;
    private String contactNumber;

    public LibraryMember(int memberId, String memberName, String contactNumber) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.contactNumber = contactNumber;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void displayMemberDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Member Name: " + memberName);
        System.out.println("Contact Number: " + contactNumber);
    }
}

class Student extends LibraryMember {
    private String department;
    private int year;

    public Student(int memberId, String memberName, String contactNumber,
                   String department, int year) {
        super(memberId, memberName, contactNumber);
        this.department = department;
        this.year = year;
    }

    @Override
    public void displayMemberDetails() {
        super.displayMemberDetails();
        System.out.println("Member Type: Student");
        System.out.println("Department: " + department);
        System.out.println("Year: " + year);
    }
}

class Faculty extends LibraryMember {
    private String department;
    private String designation;

    public Faculty(int memberId, String memberName, String contactNumber,
                   String department, String designation) {
        super(memberId, memberName, contactNumber);
        this.department = department;
        this.designation = designation;
    }

    @Override
    public void displayMemberDetails() {
        super.displayMemberDetails();
        System.out.println("Member Type: Faculty");
        System.out.println("Department: " + department);
        System.out.println("Designation: " + designation);
    }
}

class IssueRecord {
    private int issueId;
    private int bookId;
    private int memberId;
    private boolean active;

    public IssueRecord(int issueId, int bookId, int memberId) {
        this.issueId = issueId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.active = true;
    }

    public int getIssueId() {
        return issueId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

abstract class LibraryOperations {
    abstract void addBook(Book book);
    abstract void displayBooks();
    abstract void issueBook(int bookId, int memberId);
    abstract void returnBook(int bookId, int memberId);
}

class Library extends LibraryOperations {

    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<LibraryMember> memberList = new ArrayList<>();

    HashMap<Integer, Book> bookMap = new HashMap<>();
    LinkedHashMap<Integer, IssueRecord> issueRecords = new LinkedHashMap<>();
    HashSet<Integer> issuedBookIds = new HashSet<>();

    Scanner sc = new Scanner(System.in);

    int nextMemberId = 1001;
    int nextIssueId = 1;

    @Override
    void addBook(Book book) {
        if (bookMap.containsKey(book.getBookId())) {
            System.out.println("Duplicate Book ID!");
            return;
        }

        bookList.add(book);
        bookMap.put(book.getBookId(), book);

        System.out.println("Book Added Successfully!");
    }

    @Override
    void displayBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No Books Available!");
            return;
        }

        for (Book b : bookList) {
            b.displayBook();
            System.out.println("--------------------");
        }

        System.out.println("Total Books: " + bookList.size());

        System.out.println("Books with Zero Copies:");

        boolean found = false;

        for (Book b : bookList) {
            if (b.getAvailableCopies() == 0) {
                System.out.println(b.getBookName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books are currently unavailable.");
        }
    }

    void searchBook(int bookId) {
        if (bookMap.containsKey(bookId)) {
            bookMap.get(bookId).displayBook();
        } else {
            System.out.println("Book Not Found!");
        }
    }

    void registerStudent() {
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Contact Number: ");
        String contact = sc.nextLine();

        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        System.out.print("Enter Year: ");
        int year = sc.nextInt();
        sc.nextLine();

        Student s = new Student(nextMemberId, name, contact, department, year);
        memberList.add(s);

        System.out.println("Student Registered Successfully!");
        System.out.println("Member ID: " + nextMemberId);

        nextMemberId++;
    }

    void registerFaculty() {
        System.out.print("Enter Faculty Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Contact Number: ");
        String contact = sc.nextLine();

        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        System.out.print("Enter Designation: ");
        String designation = sc.nextLine();

        Faculty f = new Faculty(nextMemberId, name, contact, department, designation);
        memberList.add(f);

        System.out.println("Faculty Registered Successfully!");
        System.out.println("Member ID: " + nextMemberId);

        nextMemberId++;
    }

    void displayMembers() {
        if (memberList.isEmpty()) {
            System.out.println("No Members Registered!");
            return;
        }

        for (LibraryMember m : memberList) {
            m.displayMemberDetails();
            System.out.println("--------------------");
        }
    }

    LibraryMember searchMember(int memberId) {
        for (LibraryMember m : memberList) {
            if (m.getMemberId() == memberId) {
                return m;
            }
        }

        return null;
    }

    void searchMemberDetails(int memberId) {
        LibraryMember m = searchMember(memberId);

        if (m != null) {
            m.displayMemberDetails();
        } else {
            System.out.println("Member Not Found!");
        }
    }

    @Override
    void issueBook(int bookId, int memberId) {
        LibraryMember member = searchMember(memberId);
        Book book = bookMap.get(bookId);

        if (member == null) {
            System.out.println("Member Not Found!");
            return;
        }

        if (book == null) {
            System.out.println("Book Not Found!");
            return;
        }

        if (book.getAvailableCopies() <= 0) {
            System.out.println("No Copies Available!");
            return;
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        IssueRecord record = new IssueRecord(nextIssueId, bookId, memberId);

        issueRecords.put(nextIssueId, record);
        issuedBookIds.add(bookId);

        nextIssueId++;

        System.out.println("Book Issued Successfully!");
        System.out.println("Member: " + member.getMemberName());
        System.out.println("Book: " + book.getBookName());
        System.out.println("Available Copies: " + book.getAvailableCopies());
    }

    @Override
    void returnBook(int bookId, int memberId) {
        IssueRecord activeRecord = null;

        for (IssueRecord record : issueRecords.values()) {
            if (record.getBookId() == bookId
                    && record.getMemberId() == memberId
                    && record.isActive()) {
                activeRecord = record;
                break;
            }
        }

        if (activeRecord == null) {
            System.out.println("No Active Issue Record Found!");
            return;
        }

        Book book = bookMap.get(bookId);

        if (book != null) {
            book.setAvailableCopies(book.getAvailableCopies() + 1);
        }

        activeRecord.setActive(false);

        boolean stillIssued = false;

        for (IssueRecord record : issueRecords.values()) {
            if (record.getBookId() == bookId && record.isActive()) {
                stillIssued = true;
                break;
            }
        }

        if (!stillIssued) {
            issuedBookIds.remove(bookId);
        }

        System.out.println("Book Returned Successfully!");
    }

    void displayIssuedBooks(boolean activeOnly) {
        boolean found = false;

        for (IssueRecord record : issueRecords.values()) {
            if (!activeOnly || record.isActive()) {
                Book book = bookMap.get(record.getBookId());
                LibraryMember member = searchMember(record.getMemberId());

                System.out.println("Issue ID: " + record.getIssueId());
                System.out.println("Member ID: " + record.getMemberId());
                System.out.println("Book ID: " + record.getBookId());

                if (book != null) {
                    System.out.println("Book Name: " + book.getBookName());
                }

                if (member != null) {
                    System.out.println("Member Name: " + member.getMemberName());
                }

                System.out.println("Status: " +
                        (record.isActive() ? "Issued" : "Returned"));

                System.out.println("--------------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Issue Records Found!");
        }
    }

    void generateReport() {
        int totalCopies = 0;
        int activeIssues = 0;

        for (Book b : bookList) {
            totalCopies += b.getAvailableCopies();
        }

        for (IssueRecord record : issueRecords.values()) {
            if (record.isActive()) {
                activeIssues++;
            }
        }

        System.out.println("Total Books Registered: " + bookList.size());
        System.out.println("Total Members: " + memberList.size());
        System.out.println("Active Issue Records: " + activeIssues);
        System.out.println("Total Available Copies: " + totalCopies);

        System.out.println("Books with Zero Copies:");

        boolean found = false;

        for (Book b : bookList) {
            if (b.getAvailableCopies() == 0) {
                System.out.println(b.getBookName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books are currently unavailable.");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        while (true) {
            System.out.println("\n========================================");
            System.out.println("       LIBRARY MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Register Student");
            System.out.println("5. Register Faculty");
            System.out.println("6. Display All Members");
            System.out.println("7. Search Member");
            System.out.println("8. Issue Book");
            System.out.println("9. Return Book");
            System.out.println("10. Display Issued Books");
            System.out.println("11. Generate Library Report");
            System.out.println("12. Exit");

            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Book Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Author Name: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Available Copies: ");
                    int copies = sc.nextInt();
                    sc.nextLine();

                    if (copies < 0) {
                        System.out.println("Copies cannot be negative!");
                    } else {
                        Book b = new Book(id, name, author, category, copies);
                        lib.addBook(b);
                    }
                    break;

                case 2:
                    lib.displayBooks();
                    break;

                case 3:
                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    lib.searchBook(id);
                    break;

                case 4:
                    lib.registerStudent();
                    break;

                case 5:
                    lib.registerFaculty();
                    break;

                case 6:
                    lib.displayMembers();
                    break;

                case 7:
                    System.out.print("Enter Member ID: ");
                    int memberId = sc.nextInt();
                    sc.nextLine();
                    lib.searchMemberDetails(memberId);
                    break;

                case 8:
                    System.out.print("Enter Member ID: ");
                    memberId = sc.nextInt();

                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    lib.issueBook(id, memberId);
                    break;

                case 9:
                    System.out.print("Enter Member ID: ");
                    memberId = sc.nextInt();

                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();
                    sc.nextLine();

                    lib.returnBook(id, memberId);
                    break;

                case 10:
                    System.out.println("1. Active Issues Only");
                    System.out.println("2. All Issue Records");
                    System.out.print("Enter Choice: ");
                    int option = sc.nextInt();
                    sc.nextLine();

                    if (option == 1) {
                        lib.displayIssuedBooks(true);
                    } else if (option == 2) {
                        lib.displayIssuedBooks(false);
                    } else {
                        System.out.println("Invalid Choice!");
                    }
                    break;

                case 11:
                    lib.generateReport();
                    break;

                case 12:
                    System.out.println("Exiting Library System...");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}