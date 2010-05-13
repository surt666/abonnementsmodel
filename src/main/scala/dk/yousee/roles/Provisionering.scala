package dk.yousee.roles

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:50:54 PM
 */

trait Provisionering {
  def provisionerSigma : Unit = {
    println("SIGMA")
  }

  def provisionerStalone : Unit = {
    println("Stalone")
  }
 
}