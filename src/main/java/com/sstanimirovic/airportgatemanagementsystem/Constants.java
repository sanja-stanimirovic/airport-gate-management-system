package com.sstanimirovic.airportgatemanagementsystem;

public class Constants {

    // regex
    public static final String REGEX_FLIGHT_NUMBER = "^[A-Z\\d]{2}[A-Z]?\\d{1,4}[A-Z]?$";
    public static final String REGEX_TIME = "^([0-1]?\\d|2[0-3])(?::([0-5]\\d))?(?::([0-5]?\\d))$";

    // labels
    public static final String LABEL_MESSAGE = "message";
    public static final String LABEL_TIMESTAMP = "timestamp";

    // validation messages
    public static final String MESSAGE_GATE_NUMBER_INVALID = "The gate number is invalid";
    public static final String MESSAGE_FLIGHT_NUMBER_INVALID = "The flight number is invalid";
    public static final String MESSAGE_TIME_FORMAT_INVALID = "Time format should be HH:MM:SS";

    // error messages
    public static final String MESSAGE_SERVER_ERROR = "Server error";
    public static final String MESSAGE_FLIGHT_NOT_FOUND = "The flight is not found";
    public static final String MESSAGE_GATE_NOT_FOUND = "The gate is not found";
    public static final String MESSAGE_GATE_NOT_ASSIGNED = "The gate for this flight is not assigned";
    public static final String MESSAGE_GATE_OCCUPIED = "The gate is occupied by another flight";
    public static final String MESSAGE_ALREADY_ASSIGNED = "The gate is already assigned to this flight";
    public static final String MESSAGE_GATE_UNAVAILABLE = "The gate is unavailable at the moment";
    public static final String MESSAGE_WORKING_HOURS_INVALID = "Opening Hours should not be after closing hours";

}
