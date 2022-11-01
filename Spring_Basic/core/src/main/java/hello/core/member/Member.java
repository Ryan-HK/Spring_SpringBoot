package hello.core.member;

public class Member {

    private Long id;
    private String name;
    private Grade grade;

    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    } // Constructor

    public Long getId() {
        return id;
    } // getId

    public String getName() {
        return name;
    } // getName

    public Grade getGrade() {
        return grade;
    } // getGrade

    public void setId(Long id) {
        this.id = id;
    } // setId

    public void setName(String name) {
        this.name = name;
    } // setName

    public void setGrade(Grade grade) {
        this.grade = grade;
    } // setGrade

} // end class
