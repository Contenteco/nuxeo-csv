/*
 * (C) Copyright 2012 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Thomas Roger
 */

package org.nuxeo.ecm.csv;

import java.io.Serializable;
import java.util.Locale;

import org.nuxeo.common.utils.i18n.I18NUtils;

/**
 * @author <a href="mailto:troger@nuxeo.com">Thomas Roger</a>
 * @since 5.7
 */
public class CSVImportLog implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum Status {
        SUCCESS, SKIPPED, ERROR
    }

    protected final long line;

    protected final Status status;

    protected final String message;

    protected final String localizedMessage;

    protected final String[] params;

    public CSVImportLog(long line, Status status, String message, String localizedMessage, String... params) {
        this.line = line;
        this.status = status;
        this.message = message;
        this.localizedMessage = localizedMessage;
        this.params = params;
    }

    public long getLine() {
        return line;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public Object[] getLocalizedMessageParams() {
        return params;
    }

    public String getI18nMessage(Locale locale) {
        return I18NUtils.getMessageString("messages", getLocalizedMessage(), getLocalizedMessageParams(), locale);
    }

    public String getI18nMessage() {
        return getI18nMessage(Locale.ENGLISH);
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    public boolean isSkipped() {
        return status == Status.SKIPPED;
    }

    public boolean isError() {
        return status == Status.ERROR;
    }
}
