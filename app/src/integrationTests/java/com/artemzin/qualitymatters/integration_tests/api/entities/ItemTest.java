package com.artemzin.qualitymatters.integration_tests.api.entities;

import com.artemzin.qualitymatters.QualityMattersRobolectricTestRunner;
import com.artemzin.qualitymatters.api.entities.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(QualityMattersRobolectricTestRunner.class)
public class ItemTest {

    // Why test JSON serialization/deserialization?
    // 1. Update JSON libraries without worrying about breaking changes.
    // 2. Be sure that @JsonIgnore and similar annotations do not affect expected behavior (cc @karlicos).
    @Test
    public void fromJson() throws IOException {
        ObjectMapper objectMapper = QualityMattersRobolectricTestRunner.qualityMattersApp().applicationComponent().objectMapper();

        Item item = objectMapper.readValue("{ " +
                        "\"id\": \"test_id\", " +
                        "\"image_preview_url\": \"some_url\"," +
                        "\"title\": \"Test title\", " +
                        "\"short_description\": \"Test short description\"" +
                        "}",
                Item.class);

        assertThat(item.id()).isEqualTo("test_id");
        assertThat(item.imagePreviewUrl()).isEqualTo("some_url");
        assertThat(item.title()).isEqualTo("Test title");
        assertThat(item.shortDescription()).isEqualTo("Test short description");
    }

}
