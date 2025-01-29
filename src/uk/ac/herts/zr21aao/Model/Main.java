package uk.ac.herts.zr21aao.Model;

import uk.ac.herts.zr21aao.controller.ParcelList;
import uk.ac.herts.zr21aao.controller.*;

import javax.swing.SwingUtilities;

import uk.ac.herts.zr21aao.Model.*;
import uk.ac.herts.zr21aao.view.*;

@SuppressWarnings("unused")
public class Main {
    public static void main(String[] args) {
    	SwingUtilities.invokeLater(LoginFrame::new);

//        ParcelList parcelList = ParcelList.getInstance();
//        CustomerList customerList = CustomerList.getInstance();
//        customerList.printCustomers();
//        Staff staff1 = Staff.initStaff();
//        System.out.println(staff1.toString());
//     
   
        

//
//        Parcel parcel1 = new Parcel();
//        parcel1.setParcelID("P123");
//        parcel1.setWeight(10);
//        parcel1.setHeight(20);
//        parcel1.setWidth(15);
//        parcel1.setLength(25);
//        parcel1.setDaysInDepot(5);
//        parcel1.setDiscount(10.5f);
//        parcel1.getStatus();

//        Parcel parcel2 = new Parcel();
//        parcel2.setParcelID("P456");
//        parcel2.setWeight(5);
//        parcel2.setHeight(10);
//        parcel2.setWidth(8);
//        parcel2.setLength(12);
//        parcel2.setDaysInDepot(2);
//        parcel2.setDiscount(5.0f);
//        
//
//        
//        Parcel parcel3 = new Parcel();
//        parcel3.setParcelID("A566");
//        parcel3.setWeight(5);
//        parcel3.setHeight(10);
//        parcel3.setWidth(8);
//        parcel3.setLength(12);
//        parcel3.setDaysInDepot(2);
//        parcel3.setDiscount(5.0f);
//
//
//
//        System.out.println("Adding parcel 1: " + parcelList.addParcel(parcel1));
//        System.out.println("Adding parcel 2: " + parcelList.addParcel(parcel2));
//        System.out.println("Adding parcel 3: " + parcelList.addParcel(parcel3));
//

//        System.out.println("Adding duplicate parcel 1: " + parcelList.addParcel(parcel1));
//
//   
//        Parcel retrievedParcel = parcelList.getParcel("A566");
//        if (retrievedParcel != null) {
//            System.out.println("Retrieved Parcel: " + retrievedParcel.getParcelID());
//        } else {
//            System.out.println("Parcel not found!");
//        }
//

//        System.out.println("Removing parcel 1: " + parcelList.removeParcel(parcel1));
//        System.out.println("Removing parcel 1 again: " + parcelList.removeParcel(parcel1));
//
//  
//        System.out.println("Remaining parcels in the list:");
//        for (Parcel p : parcelList.getParcels()) {
//            System.out.println(p);
//        }
//
//       
//        System.out.println("Parcel list modified? " + parcelList.isModified());
    }
}