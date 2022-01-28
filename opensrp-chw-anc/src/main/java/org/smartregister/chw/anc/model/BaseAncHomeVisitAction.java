package org.smartregister.chw.anc.model;

import android.content.Context;

public class BaseAncHomeVisitAction extends BaseHomeVisitAction {

    protected BaseAncHomeVisitAction(Builder builder) throws ValidationException {
        super(builder);
    }

    public static class Builder extends BaseHomeVisitAction.Builder<Builder> {

        public Builder(Context context, String title) {
            super(context, title);
        }

        @Override
        public Builder getThis() {
            return this;
        }

        public BaseAncHomeVisitAction build () throws ValidationException {
            return new BaseAncHomeVisitAction(this);
        }
    }
}
