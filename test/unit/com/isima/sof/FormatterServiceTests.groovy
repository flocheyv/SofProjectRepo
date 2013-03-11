package com.isima.sof



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FormatterService)
class FormatterServiceTests {

    void testNumberFormat() {
		
		assertEquals service.coolFormat((Double)1000, 0), '1k'
		assertEquals service.coolFormat((Double)5821, 0), '5.8k'
		assertEquals service.coolFormat((Double)10500, 0), '10k'
		assertEquals service.coolFormat((Double)101800, 0), '101k'
		assertEquals service.coolFormat((Double)2000000, 0), '2m'
		assertEquals service.coolFormat((Double)7800000, 0), '7.8m'
		assertEquals service.coolFormat((Double)92150000, 0), '92m'
		assertEquals service.coolFormat((Double)123200000, 0), '123m'
		assertEquals service.coolFormat((Double)9999999, 0), '9.9m'
    }
}
