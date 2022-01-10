package org.smartregister.chw.anc.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class JsonFormUtilsTest {

    @Test
    public void getValueArrayReturnsCorrectArrayFromJsonForm() throws JSONException {
        JSONObject formJsonObject = new JSONObject(getSampleJsonFormString());
        JSONArray resultArray = JsonFormUtils.getValueArray(formJsonObject, org.smartregister.util.JsonFormUtils.STEP1, "no_children_no");
        System.out.println(resultArray);
        Assert.assertNotNull(resultArray);
        Assert.assertEquals(2, resultArray.length());
        Assert.assertEquals("surname", resultArray.getJSONObject(0).optString("key"));
    }

    public String getSampleJsonFormString() {
        return "{\n" +
                "  \"validate_on_submit\": true,\n" +
                "  \"show_errors_on_submit\": false,\n" +
                "  \"count\": \"1\",\n" +
                "  \"encounter_type\": \"Pregnancy Outcome\",\n" +
                "  \"step1\": {\n" +
                "    \"title\": \"{{anc_pregnancy_outcome.step1.title}}\",\n" +
                "    \"fields\": [\n" +
                "      {\n" +
                "        \"key\": \"no_children_no\",\n" +
                "        \"type\": \"repeating_group\",\n" +
                "        \"reference_edit_text_hint\": \"{{anc_pregnancy_outcome.step1.no_children_no.reference_edit_text_hint}}\",\n" +
                "        \"value\": [\n" +
                "          {\n" +
                "            \"key\": \"surname\",\n" +
                "            \"openmrs_entity_parent\": \"\",\n" +
                "            \"type\": \"edit_text\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"key\": \"same_as_fam_name_chk\",\n" +
                "            \"openmrs_entity_parent\": \"\",\n" +
                "            \"type\": \"check_box\"\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }
}
