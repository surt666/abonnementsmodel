package dk.yousee.repository

import collection.mutable.{HashMap, Map}

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 13, 2010
 * Time: 11:22:02 AM
 */
object ProduktRepo {
  val products = new HashMap[Int,Produkt]

  products += (1101001 -> new Produkt(1101001,"Grundpakke",149.00,Nil,null))
  products += (1101101 -> new Produkt(1101101,"Mellempakke",199.00,Nil,null))
  products += (1101201 -> new Produkt(1101201,"Fuldpakke",299.00,Nil,null))
  val bbProps = Map[Properties.Value,String]()
  bbProps += (Properties.ProvSystem -> "Stalone")
  bbProps += (Properties.ProvNum -> "1301101PROV")
  products += (1301101 -> new Produkt(1301101,"8 Mbit/s Bredbaand",129.00,Nil,bbProps))
  bbProps += (Properties.ProvNum -> "1301002PROV")
  products += (1301002 -> new Produkt(1301002,"15 Mbit/s Bredbaand",189.00,Nil,bbProps))
  bbProps += (Properties.ProvNum -> "1301003PROV")
  products += (1301003 -> new Produkt(1301003,"50 Mbit/s Bredbaand",339.00,Nil,bbProps))
  val digiKortProps = Map[Properties.Value,String]()
  digiKortProps += (Properties.ProvSystem -> "Sigma")
  digiKortProps += (Properties.ProvNum -> "1201001PROV")
  digiKortProps += (Properties.LogistikNum -> "1201001LOG")
  products += (1201001 -> new Produkt(1201001,"Digi Kort",30.00,Nil,digiKortProps))
  val digiBoksProps = Map[Properties.Value,String]()
  digiBoksProps += (Properties.LogistikNum -> "1201101LOG")
  products += (1201101 -> new Produkt(1201101,"Digi Boks",59.00,Nil,digiBoksProps))
  products += (1203101 -> new Produkt(1203101,"YouSee Plus",79.00,List(1201001,1201101),null))
  products += (1701001 -> new Produkt(1701001,"YouSee Silver",178.45,List(1101001,1301101,1203101),null))

  def findProdukt(produktId : Int) : Produkt = {
    products(produktId)
  }
}