package dk.yousee.utilities

import dk.yousee.repository.{Properties, Produkt, ProduktRepo}
import dk.yousee.roles.{Provisionering, BestilFraLager}
import dk.yousee.abonnement.{Periode, LeveringsAftale}
import java.util.Date
import collection.mutable.Map
/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 13, 2010
 * Time: 4:20:55 PM
 */

object LeveringsAftaleUtility {
  def opretLeveringsAftale(abonId : Int, betaler : Int, forbruger : Int, produktId : Int, provisioneringsSystem : String, provisioneringsNummer : String, logistikNummer : String, leveringsPeriode : Periode, properties : scala.collection.mutable.Map[String,String]) : LeveringsAftale = {
    if (provisioneringsSystem != "" && logistikNummer != "") {
      var l = new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties) with Provisionering with BestilFraLager
      l.bestilFraIRIS(logistikNummer)
      provisioneringsSystem match {
        case "Stalone" => l.provisionerStalone(provisioneringsNummer)
        case "Sigma" => l.provisionerSigma(provisioneringsNummer)
        case _ =>
      }
      l
    } else if (provisioneringsSystem != "" && logistikNummer == "") {
      val l = new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties) with Provisionering
      provisioneringsSystem match {
        case "Stalone" => l.provisionerStalone(provisioneringsNummer)
        case "Sigma" => l.provisionerSigma(provisioneringsNummer)
        case _ =>
      }
      l
    } else if (provisioneringsSystem == "" && logistikNummer != "") {
      val l = new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties) with BestilFraLager
      l.bestilFraIRIS(logistikNummer)
      l
    } else {
      new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties)
    }
  }

  def findAlleLeveringsAftaler(p : Produkt,abonId : Int, betaler : Int, forbruger : Int) : List[LeveringsAftale] = {
    val leveringsPeriode = new Periode(new Date,new Date) //todo beregn
    val properties = new scala.collection.mutable.HashMap[String,String]
    var leveringsAftaler = List[LeveringsAftale]()

    for (bpId <- findAlleBundleProdukter(p)) {
      val bp = ProduktRepo.findProdukt(bpId)
      val provisioneringsSystem = findProperty(bp.properties,Properties.ProvSystem)
      val provisioneringsNummer = findProperty(bp.properties,Properties.ProvNum)
      val logistikNummer = findProperty(bp.properties,Properties.LogistikNum)
      leveringsAftaler = opretLeveringsAftale(abonId,betaler,forbruger,bp.id,provisioneringsSystem.getOrElse(""),provisioneringsNummer.getOrElse(""),logistikNummer.getOrElse(""),leveringsPeriode,properties) :: leveringsAftaler
    }
    val provisioneringsSystem = findProperty(p.properties,Properties.ProvSystem)
    val provisioneringsNummer = findProperty(p.properties,Properties.ProvNum)
    val logistikNummer = findProperty(p.properties,Properties.LogistikNum)
    leveringsAftaler = opretLeveringsAftale(abonId,betaler,forbruger,p.id,provisioneringsSystem.getOrElse(""),provisioneringsNummer.getOrElse(""),logistikNummer.getOrElse(""),leveringsPeriode,properties) :: leveringsAftaler
    leveringsAftaler
  }

  def findAlleBundleProdukter(p : Produkt) : List[Int] = {
    var bpsAll = List[Int]()
    def addBp(bl : List[Int], bps : List[Int]) {
      for (bpId <- bl) {
        val bp = ProduktRepo.findProdukt(bpId)
        if (bp.bundleProducts == Nil) {
          bpsAll = bpId :: bpsAll
        } else {
          addBp(bp.bundleProducts,bpsAll)
        }
      }
    }
    addBp(p.bundleProducts,bpsAll)
    bpsAll
  }

  def findProperty(properties : Map[Properties.Value,String], property : Properties.Value) : Option[String] = {
    if (properties != null && properties.keySet.contains(property))
      Some(properties(property))
    else
      None
  }
}