package enums;

public enum OrderStatus {

	NONE,					// No status - placeholder
	ORDER,					// New order
	AWAITING_PREPERATION,	// Car has just arrived so can't be viewed or sold
    FORECOURT,				// Can be sold
    TEST,					// On a test drive
    SOLD,					// Sold awaiting delivery
    DELIVERED;				// Order complete
}
