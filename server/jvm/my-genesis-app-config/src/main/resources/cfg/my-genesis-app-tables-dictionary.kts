/**
 * System              : Genesis Business Library
 * Sub-System          : multi-pro-code-test Configuration
 * Version             : 1.0
 * Copyright           : (c) Genesis
 * Date                : 2022-03-18
 * Function : Provide table definition config for multi-pro-code-test.
 *
 * Modification History
 */

tables {

    table (name = "TRADE", id = 2000, audit = details(id=2100, sequence="TA")) {
        sequence(TRADE_ID, "TR")
        QUANTITY
        PRICE not null
        FX_RATE
        SYMBOL
        DIRECTION
        COUNTERPARTY_ID
        INSTRUMENT_ID not null
        TRADE_DATE
        ENTERED_BY
        TRADE_STATUS

        primaryKey {
            TRADE_ID
        }
    }


}