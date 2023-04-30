class Person {
    def age, gender
    Person (age) {
        this.age = age
    }

    Person (age, gender) {
        this.age = age
        this.gender = gender
    }
}

def student = new Person(age: 10, gender: '男')
println student.age.toString()
println student.gender.toString()

