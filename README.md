# Room-Booking-Service
<br/>
<h2>Intro</h2>
Demo project for Room booking service in which a room can be created, booked for customers in specific check-in/out dates 
and delete a booking. 
Using spring boot and Postgres DB. The project is Docker compatable and creates two docker containers for the DB and Spring applicaiton. 
<br/>
<h2>Run</h2>
Run Application with 

```

docker-compose up

``` 


<br/>
List of API methods to access 
<br/>
<table>
<tr>
<td> Create new room </td>
<td>http://localhost:8080/createRoom/{roomType} [Single,Double,Suite]</td>
</tr>
<tr>
<td>List available rooms with each room's bookings list</td>
<td>localhost:8080/rooms/</td>
</tr>
<tr>
<td>List all active bookings</td>
<td>localhost:8080/bookings/</td>
</tr>
<tr>
<td>Create new room's booking</td>
<td>localhost:8080/booking/?roomId={roomId}&checkin={checkinDate}&checkout={checkoutDate}/</td>
</tr>
<tr>
<td>Delete a room's booking</td>
<td>localhost:8080/bookings/{bookingId}</td>
</tr>
</table>
