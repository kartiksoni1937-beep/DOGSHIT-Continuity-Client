/*
 * Decompiled with CFR.
 */
package me.pepperbell.continuity.event.events;

import java.util.List;
import me.pepperbell.continuity.event.CancellableEvent;
import me.pepperbell.continuity.event.Listener;
import net.minecraft.class_1713;

public interface ClickSlotListener
extends Listener {
    public void onClickSlot(ClickSlotEvent var1);

    public static class ClickSlotEvent
    extends CancellableEvent<ClickSlotListener> {
        private final int windowId;
        private final int slotId;
        private final int button;
        private final class_1713 actionType;

        public ClickSlotEvent(int windowId, int slotId, int button, class_1713 actionType) {
            this.windowId = windowId;
            this.slotId = slotId;
            this.button = button;
            this.actionType = actionType;
        }

        public int getWindowId() {
            return this.windowId;
        }

        public int getSlotId() {
            return this.slotId;
        }

        public int getButton() {
            return this.button;
        }

        public class_1713 getActionType() {
            return this.actionType;
        }

        @Override
        public void fire(List<ClickSlotListener> listeners) {
            listeners.forEach(e -> e.onClickSlot(this));
        }

        @Override
        public Class<ClickSlotListener> getListenerType() {
            return ClickSlotListener.class;
        }
    }
}

