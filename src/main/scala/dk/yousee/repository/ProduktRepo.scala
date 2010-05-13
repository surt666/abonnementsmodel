package dk.yousee.repository

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 13, 2010
 * Time: 11:22:02 AM
 */

object ProduktRepo {
  val products = new scala.collection.mutable.HashMap[Int,Produkt]
  products += (1101001 -> new Produkt(1101001,"Grundpakke",149.00,Nil))
  products += (1101101 -> new Produkt(1101101,"Mellempakke",199.00,Nil))
  products += (1101201 -> new Produkt(1101201,"Fuldpakke",299.00,Nil))
  products += (1301101 -> new Produkt(1301101,"8 Mbit/s Bredbaand",129.00,Nil))
  products += (1301002 -> new Produkt(1301002,"15 Mbit/s Bredbaand",189.00,Nil))
  products += (1301003 -> new Produkt(1301003,"50 Mbit/s Bredbaand",339.00,Nil))
  products += (1201001 -> new Produkt(1201001,"Digi Kort",30.00,Nil))
  products += (1201101 -> new Produkt(1201101,"Digi Boks",59.00,Nil))
  products += (1203101 -> new Produkt(1203101,"YouSee Plus",79.00,List(1201001,1201101)))
  products += (1701001 -> new Produkt(1701001,"YouSee Silver",178.45,List(1101001,1301101,1203101)))

  def findProdukt(produktId : Int) : Produkt = {
    products(produktId)
  }
}