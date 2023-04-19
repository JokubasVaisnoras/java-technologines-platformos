package lt.vu.rest;

import lombok.*;
import lt.vu.rest.contracts.PassengerDto;
import lt.vu.entities.Passengers;
import lt.vu.persistence.PassengersDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/passenger")
public class PassengersController {

    @Inject
    @Setter @Getter
    private PassengersDAO passengersDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Long id) {
        Passengers passenger = passengersDAO.findOne(id);
        if (passenger == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setName(passenger.getName());
        passengerDto.setLast_name(passenger.getLast_name());
        passengerDto.setAge(passenger.getAge());
        passengerDto.setPlane(passenger.getPlanes().getAirline());

        return Response.ok(passengerDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Long passengerId,
            PassengerDto passengerData) {
        try {
            Passengers existingPassenger = passengersDAO.findOne(passengerId);
            if (existingPassenger == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingPassenger.setName(passengerData.getName());
            existingPassenger.setLast_name(passengerData.getLast_name());
            existingPassenger.setAge(passengerData.getAge());
            passengersDAO.update(existingPassenger);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}