package com.example.inav;

import android.content.Context;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.tech.Ndef;

public class WriteFragment {

	public static boolean writeTag(Context context, Tag tag, String data) {     
	    // Record to launch Play Store if app is not installed
	    NdefRecord appRecord = NdefRecord.createApplicationRecord(context.getPackageName());
	 
	    // Record with actual data we care about
	    NdefRecord relayRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
	                                            new String("application/" + context.getPackageName()).getBytes(Charset.forName("US-ASCII")),
	                                            null, data.getBytes());
	 
	    // Complete NDEF message with both records
	    NdefMessage message = new NdefMessage(new NdefRecord[] {relayRecord, appRecord});
	 
	    try {
	        // If the tag is already formatted, just write the message to it
	        Ndef ndef = Ndef.get(tag);
	        if(ndef != null) {
	            ndef.connect();
	 
	            // Make sure the tag is writable
	            if(!ndef.isWritable()) {
	                DialogUtils.displayErrorDialog(context, R.string.nfcReadOnlyErrorTitle, R.string.nfcReadOnlyError);
	                return false;
	            }
	 
	            // Check if there's enough space on the tag for the message
	            int size = message.toByteArray().length;
	            if(ndef.getMaxSize() < size) {
	                DialogUtils.displayErrorDialog(context, R.string.nfcBadSpaceErrorTitle, R.string.nfcBadSpaceError);
	                return false;
	            }
	 
	            try {
	                // Write the data to the tag
	                ndef.writeNdefMessage(message);
	 
	                DialogUtils.displayInfoDialog(context, R.string.nfcWrittenTitle, R.string.nfcWritten);
	                return true;
	            } catch (TagLostException tle) {
	                DialogUtils.displayErrorDialog(context, R.string.nfcTagLostErrorTitle, R.string.nfcTagLostError);
	                return false;
	            } catch (IOException ioe) {
	                DialogUtils.displayErrorDialog(context, R.string.nfcFormattingErrorTitle, R.string.nfcFormattingError);
	                return false;
	            } catch (FormatException fe) {
	                DialogUtils.displayErrorDialog(context, R.string.nfcFormattingErrorTitle, R.string.nfcFormattingError);
	                return false;
	            }
	        // If the tag is not formatted, format it with the message
	        } else {
	            NdefFormatable format = NdefFormatable.get(tag);
	            if(format != null) {
	                try {
	                    format.connect();
	                    format.format(message);
	 
	                    DialogUtils.displayInfoDialog(context, R.string.nfcWrittenTitle, R.string.nfcWritten);
	                    return true;
	                } catch (TagLostException tle) {
	                    DialogUtils.displayErrorDialog(context, R.string.nfcTagLostErrorTitle, R.string.nfcTagLostError);
	                    return false;
	                } catch (IOException ioe) {
	                    DialogUtils.displayErrorDialog(context, R.string.nfcFormattingErrorTitle, R.string.nfcFormattingError);
	                    return false;
	                } catch (FormatException fe) {
	                    DialogUtils.displayErrorDialog(context, R.string.nfcFormattingErrorTitle, R.string.nfcFormattingError);
	                    return false;
	                }
	            } else {
	                DialogUtils.displayErrorDialog(context, R.string.nfcNoNdefErrorTitle, R.string.nfcNoNdefError);
	                return false;
	            }
	        }
	    } catch(Exception e) {
	        DialogUtils.displayErrorDialog(context, R.string.nfcUnknownErrorTitle, R.string.nfcUnknownError);
	    }
	 
	    return false;
	}
}
