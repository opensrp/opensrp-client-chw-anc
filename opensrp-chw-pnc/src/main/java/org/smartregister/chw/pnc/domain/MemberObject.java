package org.smartregister.chw.pnc.domain;

import org.smartregister.chw.anc.util.DBConstants;
import org.smartregister.commonregistry.CommonPersonObjectClient;
import org.smartregister.util.Utils;

@SuppressWarnings("serial")
public class MemberObject extends org.smartregister.chw.anc.domain.MemberObject {
    public String pregnancyOutcome;
    public String miscarriageDate;
    public String deliveryDate;
    public String deliveryPlace;
    public String lastVisitDate;
    public String nextVisitDate;

    public MemberObject() {

    }

    public MemberObject(CommonPersonObjectClient commonPerson) {
        super(commonPerson);
        pregnancyOutcome = Utils.getValue(commonPerson.getColumnmaps(), DBConstants.KEY.PREGNANCY_OUTCOME, false);
        miscarriageDate = Utils.getValue(commonPerson.getColumnmaps(), DBConstants.KEY.MISCARRIAGE_DATE, false);
        deliveryDate = Utils.getValue(commonPerson.getColumnmaps(), DBConstants.KEY.DELIVERY_DATE, false);
        deliveryPlace = Utils.getValue(commonPerson.getColumnmaps(), DBConstants.KEY.DELIVERY_PLACE, false);
        lastVisitDate = Utils.getValue(commonPerson.getColumnmaps(), DBConstants.KEY.LAST_VISIT_DATE, false);
        nextVisitDate = Utils.getValue(commonPerson.getColumnmaps(), DBConstants.KEY.NEXT_VISIT_DATE, false);
    }


    public String getPregnancyOutcome() {
        return pregnancyOutcome;
    }

    public void setPregnancyOutcome(String pregnancyOutcome) {
        this.pregnancyOutcome = pregnancyOutcome;
    }

    public String getMiscarriageDate() {
        return miscarriageDate;
    }

    public void setMiscarriageDate(String miscarriageDate) {
        this.miscarriageDate = miscarriageDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public String getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(String nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }
}
