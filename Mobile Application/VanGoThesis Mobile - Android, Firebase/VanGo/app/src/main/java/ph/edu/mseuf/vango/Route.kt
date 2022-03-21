class Route{
    var id : String?=null
    var terminal : String?=null
    var destination : String?=null
    var driverName : String?=null
    var fareRate : String?=null
    var emailAccount : String?=null
    var plateNumber : String?=null
    var fcmNumber : String?=null
    constructor(){}

    constructor(
        id: String?,
        terminal: String?,
        destination : String?,
        driverName : String?,
        fareRate : String?,
        emailAccount : String?,
        plateNumber : String?,
        fcmNumber : String?
    ){
        this.id = id
        this.terminal = terminal
        this.destination = destination
        this.driverName = driverName
        this.fareRate = fareRate
        this.emailAccount = emailAccount
        this.plateNumber = plateNumber
        this.fcmNumber = fcmNumber
    }
}