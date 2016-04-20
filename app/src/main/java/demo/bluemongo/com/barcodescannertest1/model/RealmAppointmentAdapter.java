package demo.bluemongo.com.barcodescannertest1.model;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmAppointmentAdapter {
    public RealmAppointmentAdapter() {
    }

    Appointment getAppointmentFromRealmAppointment(RealmAppointment realmAppointment) {
        Appointment output = new Appointment();

        output.setId(realmAppointment.getId());
        output.setCustomerId(realmAppointment.getCustomerId());
        output.setAppointmentTypeId(realmAppointment.getAppointmentTypeId());
        output.setAppointmentTypeName(realmAppointment.getAppointmentTypeName());
        output.setAppointmentTypePrefix(realmAppointment.getAppointmentTypePrefix());
        output.setAppTypeHexCode(realmAppointment.getAppTypeHexCode());
        output.setIsComplete(realmAppointment.isComplete());
        output.setLocationHexCode(realmAppointment.getLocationHexCode());
        output.setLocationId(realmAppointment.getLocationId());
        output.setLocationName(realmAppointment.getLocationName());
        output.setMessageToCustomer(realmAppointment.getMessageToCustomer());
        output.setStatus(realmAppointment.getStatus());
        output.setStatusHexCode(realmAppointment.getStatusHexCode());
        output.setStatusName(realmAppointment.getStatusName());
        output.setStrAppointmentDate(realmAppointment.getStrAppointmentDate());
        output.setStrAppointmentTime(realmAppointment.getStrAppointmentTime());
        output.setStrCheckInDateTime(realmAppointment.getStrCheckInDateTime());

        return output;
    }


    public AppointmentsResponse getAppointmentsResponseFromRealmObject(RealmResults<RealmAppointmentsResponse> results) {
        AppointmentsResponse appointmentsResponse = new AppointmentsResponse();

        for(RealmAppointmentsResponse realmAppointmentsResponse : results) {
/*            for (RealmAppointment appointment : realmAppointmentsResponse.getAppointmentList()) {
                Log.i("realm appointment", appointment.getStatusName());
            }*/


            RealmList<RealmAppointment> realmAppointmentList = realmAppointmentsResponse.getAppointmentList();

            List<Appointment> appointments = new ArrayList<>();
            for (RealmAppointment realmAppointment : realmAppointmentList) {
                Appointment appointmentFromRealmAppointment = getAppointmentFromRealmAppointment(realmAppointment);
                appointments.add(appointmentFromRealmAppointment);
            }
            appointmentsResponse.setAppointmentList(appointments);

            List<AppointmentStatus> appointmentStatuses = new ArrayList<>();
            for (RealmAppointmentStatus realmAppointmentStatus : realmAppointmentsResponse.getAppointmentStatusList()) {
                AppointmentStatus appointmentStatusFromRealmAppointmentStatus = getAppointmentStatusFromRealmAppointmentStatus(realmAppointmentStatus);
                appointmentStatuses.add(appointmentStatusFromRealmAppointmentStatus);
            }
            appointmentsResponse.setAppointmentStatusList(appointmentStatuses);

            BusinessDTO businessFromRealmBusiness = getBusinessDTOFromRealmBusinessDTO(realmAppointmentsResponse.getBusiness());
            appointmentsResponse.setBusinessDTO(businessFromRealmBusiness);
        }
        return appointmentsResponse;
    }

    private BusinessDTO getBusinessDTOFromRealmBusinessDTO(RealmBusinessDTO realmBusinessDTO) {
        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setId(realmBusinessDTO.getId());
        businessDTO.setBusinessName(realmBusinessDTO.getBusinessName());
        businessDTO.setBackgroundColourHexCode(realmBusinessDTO.getBackgroundColourHexCode());
        businessDTO.setHeaderColourHexCode(realmBusinessDTO.getHeaderColourHexCode());
        businessDTO.setButtonColourHexCode(realmBusinessDTO.getButtonColourHexCode());
        businessDTO.setLogoFileName(realmBusinessDTO.getLogoFileName());
        return businessDTO;
    }

    private AppointmentStatus getAppointmentStatusFromRealmAppointmentStatus(RealmAppointmentStatus realmAppointmentStatus) {
        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setId(realmAppointmentStatus.getId());
        appointmentStatus.setName(realmAppointmentStatus.getName());
        appointmentStatus.setBusinessId(realmAppointmentStatus.getBusinessId());
        appointmentStatus.setBackgroundColourHexCode(realmAppointmentStatus.getBackgroundColourHexCode());
        appointmentStatus.setSequenceNumber(realmAppointmentStatus.getSequenceNumber());
        appointmentStatus.setCustomerInitiated(realmAppointmentStatus.isCustomerInitiated());
        return appointmentStatus;
    }




    public RealmAppointmentsResponse getRealmObjectFromAppointmentsResponse(RealmAppointmentsResponse realmAppointmentsResponse,
                                                                            AppointmentsResponse appointmentsResponse,
                                                                            Realm realm) {
        List<Appointment> appointmentList = appointmentsResponse.getAppointmentList();

        RealmList<RealmAppointment> realmAppointments = new RealmList<>();
        for (Appointment appointment : appointmentList) {
            RealmAppointment realmAppointmentFromAppointment = getRealmAppointmentFromAppointment(appointment);
            realmAppointmentFromAppointment = realm.copyToRealm(realmAppointmentFromAppointment);
            realmAppointments.add(realmAppointmentFromAppointment);
        }
        realmAppointmentsResponse.setAppointmentList(realmAppointments);

        RealmList<RealmAppointmentStatus> realmAppointmentStatuses = new RealmList<>();
        for (AppointmentStatus appointmentStatus : appointmentsResponse.getAppointmentStatusList()) {
            RealmAppointmentStatus realmAppointmentStatusFromAppointmentStatus = getRealmAppointmentStatusFromAppointmentStatus(appointmentStatus);
            realmAppointmentStatusFromAppointmentStatus = realm.copyToRealm(realmAppointmentStatusFromAppointmentStatus);
            realmAppointmentStatuses.add(realmAppointmentStatusFromAppointmentStatus);
        }

        realmAppointmentsResponse.setAppointmentStatusList(realmAppointmentStatuses);
        RealmBusinessDTO realmBusinessFromBusiness = getRealmBusinessFromBusiness(appointmentsResponse.getBusinessDTO());
        realmBusinessFromBusiness = realm.copyToRealm(realmBusinessFromBusiness);
        realmAppointmentsResponse.setBusiness(realmBusinessFromBusiness);


        return realmAppointmentsResponse;
    }

    private RealmBusinessDTO getRealmBusinessFromBusiness(BusinessDTO business) {
        RealmBusinessDTO output = new RealmBusinessDTO();
        output.setId(business.getId());
        output.setBusinessName(business.getBusinessName());
        output.setBackgroundColourHexCode(business.getBackgroundColourHexCode());
        output.setButtonColourHexCode(business.getButtonColourHexCode());
        output.setHeaderColourHexCode(business.getHeaderColourHexCode());
        output.setLogoFileName(business.getLogoFileName());
        return output;
    }

    private RealmAppointmentStatus getRealmAppointmentStatusFromAppointmentStatus(AppointmentStatus appointmentStatus) {
        RealmAppointmentStatus output = new RealmAppointmentStatus();

        output.setId(appointmentStatus.getId());
        output.setName(appointmentStatus.getName());
        output.setBackgroundColourHexCode(appointmentStatus.getBackgroundColourHexCode());
        output.setBusinessId(appointmentStatus.getBusinessId());
        output.setCustomerInitiated(appointmentStatus.isCustomerInitiated());
        output.setSequenceNumber(appointmentStatus.getSequenceNumber());

        return output;
    }

    private RealmAppointment getRealmAppointmentFromAppointment(Appointment appointment) {
        RealmAppointment output = new RealmAppointment();

        output.setId(appointment.getId());
        output.setCustomerId(appointment.getCustomerId());
        output.setAppointmentTypeId(appointment.getAppointmentTypeId());
        output.setAppointmentTypeName(appointment.getAppointmentTypeName());
        output.setAppointmentTypePrefix(appointment.getAppointmentTypePrefix());
        output.setAppTypeHexCode(appointment.getAppTypeHexCode());
        output.setComplete(appointment.isComplete());
        output.setLocationHexCode(appointment.getLocationHexCode());
        output.setLocationId(appointment.getLocationId());
        output.setLocationName(appointment.getLocationName());
        output.setMessageToCustomer(appointment.getMessageToCustomer());
        output.setStatus(appointment.getStatus());
        output.setStatusHexCode(appointment.getStatusHexCode());
        output.setStatusName(appointment.getStatusName());
        output.setStrAppointmentDate(appointment.getStrAppointmentDate());
        output.setStrAppointmentTime(appointment.getStrAppointmentTime());
        output.setStrCheckInDateTime(appointment.getStrCheckInDateTime());

        return output;
    }


}