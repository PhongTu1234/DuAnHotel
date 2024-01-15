package com.poly.Service;

import java.text.Normalizer;

import org.springframework.stereotype.Service;

@Service
public class SlugService {

    public String convertToSlug(String fullName) {
        String normalizedString = Normalizer.normalize(fullName, Normalizer.Form.NFD);

        normalizedString = normalizedString.replaceAll("[ăâ]", "a");
        normalizedString = normalizedString.replaceAll("[đĐ]", "d");
        normalizedString = normalizedString.replaceAll("[êéèẹẻẽẺÊ]", "e");
        normalizedString = normalizedString.replaceAll("[ôơ]", "o");
        normalizedString = normalizedString.replaceAll("[ưụủũ]", "u");

        normalizedString = normalizedString.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        normalizedString = normalizedString.toLowerCase().replaceAll("[^a-z0-9\\-]", "-");

        normalizedString = normalizedString.replaceAll("-+", "-");

        normalizedString = normalizedString.replaceAll("^-|-$", "");

        return normalizedString;
    }
}
