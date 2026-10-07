/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.electrostore.util;
import com.electrostore.exception.InvalidInputException;
import java.util.regex.Pattern;

/**
 *
 * @author USER
 */
public class Validator {
    private static final Pattern PHONE = Pattern.compile("^0\\d{9}$");
    private static final Pattern EMAIL = Pattern.compile("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$");

    public static void requireText(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " is required.");
        }
    }

    public static void requirePhone(String phone) throws InvalidInputException {
        requireText(phone, "Phone number");
        if (!PHONE.matcher(phone.trim()).matches()) {
            throw new InvalidInputException(
                "Phone number must be 10 digits starting with 0 (e.g. 0771234567).");
        }
    }

    public static void optionalEmail(String email) throws InvalidInputException {
        if (email != null && !email.trim().isEmpty() && !EMAIL.matcher(email.trim()).matches()) {
            throw new InvalidInputException("Email address is not valid.");
        }
    }
    public static double requirePositiveDouble(String value, String fieldName) throws InvalidInputException {
        requireText(value, fieldName);
        try {
            double parsed = Double.parseDouble(value.trim());
            if (parsed < 0) {
                throw new InvalidInputException(fieldName + " must be a positive number.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a valid numeric price.");
        }
    }

    public static int requirePositiveInt(String value, String fieldName) throws InvalidInputException {
        requireText(value, fieldName);
        try {
            int parsed = Integer.parseInt(value.trim());
            if (parsed < 0) {
                throw new InvalidInputException(fieldName + " cannot be negative.");
            }
            return parsed;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " must be a valid whole number.");
        }
    }
}
