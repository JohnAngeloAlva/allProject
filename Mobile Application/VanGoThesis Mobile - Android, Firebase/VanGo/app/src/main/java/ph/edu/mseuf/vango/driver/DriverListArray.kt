package ph.edu.vangodriver

class DriverListArray{
    var userID: String?=null
    var userName: String?=null
    var fullName: String?=null
    var age: String?=null
    var address: String?=null
    var phoneNum: String?=null
    var id: String?=null
    var vanModel: String?=null
    var plateNumber: String?=null
    var vanCapacity: String?=null
    var destination: String?=null
    var verifificationID: String?=null
    var terminal: String?=null

    constructor(){}

    constructor(
        userID: String?,
        userName: String?,
        fullName: String?,
        age: String?,
        address: String?,
        phoneNum: String?,
        id: String?,
        vanModel: String?,
        plateNumber: String?,
        vanCapacity: String?,
        destination: String?,
        verifificationID: String?,
        terminal: String?
    ){
        this.userID = userID
        this.userName = userName
        this.fullName = fullName
        this.age = age
        this.address = address
        this.phoneNum = phoneNum
        this.id = id
        this.vanModel = vanModel
        this.plateNumber = plateNumber
        this.vanCapacity = vanCapacity
        this.destination = destination
        this.verifificationID = verifificationID
        this.terminal = terminal

    }
}
