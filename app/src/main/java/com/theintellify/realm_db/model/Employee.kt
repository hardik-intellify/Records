package com.theintellify.realm_db.model

import io.realm.RealmObject


/**
 * @author {Hardik B. Mahant}
 * crated on 2/3/2020
 */
open class Employee : RealmObject {
    var eName: String? = null
    var designation: String? = null
    constructor()
    constructor(eName: String, designation: String){
        this.eName = eName
        this.designation = designation
    }

    override fun toString(): String {
        return "name: $eName designation: $designation"
    }

}