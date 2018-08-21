package org.ocmc.ioc.liturgical.schemas.models.db.docs.ontology;

import com.google.gson.annotations.Expose;

import org.ocmc.ioc.liturgical.schemas.annotations.UiWidget;
import org.ocmc.ioc.liturgical.schemas.constants.Constants;
import org.ocmc.ioc.liturgical.schemas.constants.ID_PART_TYPES;
import org.ocmc.ioc.liturgical.schemas.constants.TOPICS;
import org.ocmc.ioc.liturgical.schemas.id.managers.IdManager;

import java.text.Normalizer;
import java.util.regex.Pattern;

import org.ocmc.ioc.liturgical.schemas.models.forms.ontology.TextLiturgicalTranslationCreateForm;
import org.ocmc.ioc.liturgical.schemas.models.supers.LTKDb;
import org.ocmc.ioc.liturgical.utils.ErrorUtils;
import org.ocmc.ioc.liturgical.utils.GeneralUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.reinert.jjschema.Attributes;

/**
 * This class provides a POJO for Liturgical text.
 * Note that it is critical that all searching and comparison using this text make use of Normalizer.normalize(x,Normalizer.Form.NFC)
 * so that there is a canonical means to do so.
 * @author mac002
 *
 */
@Attributes(title = "Liturgical Text", description = "Properties for Liturgical Text.")
public class TextLiturgical extends LTKDb {
	private static final Logger logger = LoggerFactory.getLogger(TextLiturgical.class);

	private static double serialVersion = 1.1;
	private static String schema = TextLiturgical.class.getSimpleName();
	private static Pattern punctPattern = Pattern.compile("[;˙·,.;!?\\-(){}\\[\\]/:<>%͵·\"'`’_«»‘*•+…‧′|]"); // punctuation
    
	@UiWidget(Constants.UI_WIDGET_TEXTAREA)
	@Attributes(required = true, description = "The value of the text.")
	@Expose  public String value = "";

	@Attributes(id = "bottom", required = false, readonly = true, description = "Normalized text with punctuation.")
	@Expose  public String nwp = "";

	@Attributes(id = "bottom", required = false, readonly = true, description = "Normalized text with no punctuation.")
	@Expose  public String nnp = "";

	@Attributes(id = "bottom", required = false, readonly = true, description = "First five words of normalized text with no punctuation.")
	@Expose  public String nnpFirstFive = "";

	@Attributes(id = "bottom", required = false, readonly = true, description = "Value without punctuation.")
	@Expose  public String vnp = "";

	@Attributes(id = "bottom", required = false, readonly = true, description = "Line sequence number for this text within its topic.")
	@Expose  public String seq = "";

	@Attributes(id = "bottom", required = false, readonly = true, description = "Comment from / for Alwb Library topic file")
	@Expose  public String comment = "";

	@Attributes(id = "bottom", required = false, readonly = true, description = "ID of the record this one redirects to")
	@Expose  public String redirectId = "";

	public TextLiturgical(
			String library
			, String topic
			, String key
			) {
		super(
				library
				, topic
				, key
				, schema
				, serialVersion
				, TOPICS.TEXT_LITURGICAL
				);
		this.partTypeOfTopic = ID_PART_TYPES.TOPIC_FROM_ID_OF_SELECTED_LITURGICAL_TEXT;
		this.partTypeOfKey = ID_PART_TYPES.KEY_FROM_ID_OF_SELECTED_LITURGICAL_TEXT;
	}
	
	@Override
	public void setSubClassProperties (
			String json
			) {
		try {
			TextLiturgicalTranslationCreateForm form = gson.fromJson(json, TextLiturgicalTranslationCreateForm.class);
			this.setValue(form.getValue());
			this.setSeq(form.getSeq());
			if (! this.seq.startsWith(this.library)) {
				try {
					// if the sequence is using a different library, e.g. gr_gr_cog, convert it to this instance's library
					if (this.seq == null || this.seq.length() == 0) {
						this.setSeq(this.id);
					} else {
						IdManager idManager = new IdManager(this.seq);
						this.setSeq(this.id + "~" + idManager.getTopic() + "~" + idManager.getKey());
					}
				} catch (Exception e) {
					ErrorUtils.report(logger, e);
				}
			}
			this.tags = form.getTags();
		} catch (Exception e) {
			ErrorUtils.report(logger, e);
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = GeneralUtils.toNfc(value);
		try {
			if (this.key.endsWith("chapverse")) {
				this.nnp = this.value;
				this.nnpFirstFive = this.id + "~" + this.value;
				this.nwp = this.value;
			} else {
				this.nwp = Normalizer.normalize(value, Normalizer.Form.NFD)
						.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
				this.nnp = punctPattern.matcher(this.nwp).replaceAll("");
				this.vnp = punctPattern.matcher(this.value).replaceAll("").toLowerCase();
				try {
					String [] tokens = this.nnp.split(" ");
					StringBuffer sb = new StringBuffer();
					int cnt = 0;
					for (int i = 0; i < tokens.length; i++) {
						if (sb.length() > 0) {
							sb.append(" ");
						}
						sb.append(tokens[i]);
						cnt++;
						if (cnt == 5) {
							break;
						}
					}
					this.nnpFirstFive = this.id + "~" + sb.toString();
				} catch (Exception e) {
					ErrorUtils.report(logger, e);
				}
			}
		} catch (Exception e) {
			ErrorUtils.report(logger, e);
			throw e;
		}
	}

	public String getNwp() {
		return nwp;
	}

	public void setNwp(String nwp) {
		this.nwp = nwp;
	}

	public String getNnp() {
		return nnp;
	}

	public void setNnp(String nnp) {
		this.nnp = nnp;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getVnp() {
		return vnp;
	}

	public void setVnp(String vnp) {
		this.vnp = vnp;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRedirectId() {
		return redirectId;
	}

	public void setRedirectId(String redirectId) {
		this.redirectId = redirectId;
	}

	public String getNnpFirstFive() {
		return nnpFirstFive;
	}

	public void setNnpFirstFive(String nnpFirstFive) {
		this.nnpFirstFive = nnpFirstFive;
	}

}
