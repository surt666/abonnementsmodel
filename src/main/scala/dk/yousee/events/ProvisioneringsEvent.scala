package dk.yousee.events

import java.util.Date

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:49:05 PM
 */

case class ProvisioneringsEvent(val system : String, val nummer : String, val date : Date)