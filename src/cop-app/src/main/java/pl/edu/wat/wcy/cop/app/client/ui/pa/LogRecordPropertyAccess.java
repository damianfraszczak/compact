/**
 *
 */
package pl.edu.wat.wcy.cop.app.client.ui.pa;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.logging.Level;
import java.util.logging.LogRecord;
// Defines the contract for log record property access.


public interface LogRecordPropertyAccess extends PropertyAccess<LogRecord> {
    LogRecordPropertyAccess INSTANCE = GWT.create(LogRecordPropertyAccess.class);

    @Path("threadID")
    ModelKeyProvider<LogRecord> key();

    ValueProvider<LogRecord, String> message();

    ValueProvider<LogRecord, String> loggerName();

    ValueProvider<LogRecord, Long> millis();

    ValueProvider<LogRecord, Level> level();
}
