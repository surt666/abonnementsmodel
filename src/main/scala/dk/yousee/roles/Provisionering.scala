package dk.yousee.roles

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:50:54 PM
 */

trait Provisionering {
  def provisionerSigma(provNum : String) : Unit = {
    println("SIGMA " + provNum)
  }

  def provisionerStalone(provNum : String) : Unit = {
    println("Stalone " + provNum)
  }
 
}