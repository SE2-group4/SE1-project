# Graphical User Interface Prototype

Authors: Group 12

Date: 14/04/2020

Version: 1

\<Report here the GUI that you propose. You are free to organize it as you prefer. A suggested presentation matches the Use cases and scenarios defined in the Requirement document. The GUI can be shown as a sequence of graphical files (jpg, png)  >

#User

##Homepage without selected gas station:

![Homepage_without_selected_gas_station.png](../GUI/EZGas_GUI_web/Homepage_without_selected_gas_station.png)

The user can choose if order by price or distance. In this case the user has chosen to order by price, then he/she can see in the left side of the window the list of gas stations ordered by price.


##Homepage selected gas station:

![Homepage_selected_gas_station.png](../GUI/EZGas_GUI_web/Homepage_selected_gas_station.png)

The user selected one gas station (the red one) and he/she can see the list of the prices of that station on the left side of the window (sorted by positive votes).

#Authenticated User

##Homepage Authenticated Use without selected gas station:

![Homepage_Authenticated_Use_without_selected_gas_station.png](../GUI/EZGas_GUI_web/Homepage_Authenticated_Use_without_selected_gas_station.png)

The user can choose if order by price or distance (in this case by price), but also he/she can insert new gas station by the link in the top bar because is authenticated.


##Homepage Authenticated User and selected gas station:

![Homepage_Authenticated_User_selected_gas_station.png](../GUI/EZGas_GUI_web/Homepage_Authenticated_User_selected_gas_station.png)

The user selected one gas station like the image above. The only difference is that here user is authenticated, for that reason there is the possibility to insert a new gas station.


##Homepage Authenticated User (gas station reached) and selected gas station:

![Homepage_Authenticated_User_(gas_station_reached)_selected_gas_station.png](../GUI/EZGas_GUI_web/Homepage_Authenticated_User_(gas_station_reached)_selected_gas_station.png)

The authenticated user reached the gas station and he/she can insert a new price or he/she can vote one of the existing prices. If the authenticated user votes a price, this price receives one positive vote more and the other prices receive a negative one.

##Login

![Login.png](../GUI/EZGas_GUI_web/Login.png)

##Create Account

![UC5_Create_account.png](../GUI/EZGas_GUI_web/UC5_Create_account.png)

##Record new price

![UC1_Record_new_price.png](../GUI/EZGas_GUI_web/UC1_Record_new_price.png)

When the authenticated user selects "insert a new price", he/she can insert the price of the gas station reached about the fuel type selected before in the search form.


##Gas station request by the User:

![UC6_Gas_station_request_User.png](../GUI/EZGas_GUI_web/UC6_Gas_station_request_User.png)

When the authenticated user selects "insert new gas station", he/she can notify a new gas station by its address and selecting its fuel type/s.

#Administrator

##Accept or Reject gas station request by the Admin:

![Accept_or_Reject_station_request_Admin.png](../GUI/EZGas_GUI_web/Accept_or_Reject_station_request_Admin.png)

The administrator can select from the left list one gas station insert request and after that he can accept or reject this request.