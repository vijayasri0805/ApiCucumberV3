$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("CartValidation.feature");
formatter.feature({
  "line": 1,
  "name": "These test scenario\u0027s are used to test the Cart API\u0027s of Hybris",
  "description": "",
  "id": "these-test-scenario\u0027s-are-used-to-test-the-cart-api\u0027s-of-hybris",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Validate add an item to cart for guest user",
  "description": "",
  "id": "these-test-scenario\u0027s-are-used-to-test-the-cart-api\u0027s-of-hybris;validate-add-an-item-to-cart-for-guest-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "User checks the happy path",
  "keyword": "Given "
});
formatter.match({
  "location": "HeadlessProductCheckSteps.initialize_the_Remove_Cart_test_suite()"
});
formatter.result({
  "duration": 568846984,
  "error_message": "java.lang.IllegalArgumentException: parameterValue cannot be null\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:80)\n\tat org.codehaus.groovy.reflection.CachedConstructor.doConstructorInvoke(CachedConstructor.java:74)\n\tat org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrap.callConstructor(ConstructorSite.java:84)\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallConstructor(CallSiteArray.java:60)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:235)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:247)\n\tat com.jayway.restassured.internal.assertion.AssertParameter.notNull(AssertParameter.groovy:26)\n\tat com.jayway.restassured.internal.assertion.AssertParameter$notNull.callStatic(Unknown Source)\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallStatic(CallSiteArray.java:56)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callStatic(AbstractCallSite.java:194)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callStatic(AbstractCallSite.java:214)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.pathParameter(RequestSpecificationImpl.groovy:454)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:93)\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1210)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1019)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:810)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:48)\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.callCurrent(PogoInterceptableSite.java:58)\n\tat org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallCurrent(CallSiteArray.java:52)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:154)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:174)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.pathParam(RequestSpecificationImpl.groovy:472)\n\tat com.levi.api.headless.CartValidation.checkProduct(CartValidation.java:150)\n\tat com.levi.api.headless.steps.HeadlessProductCheckSteps.initialize_the_Remove_Cart_test_suite(HeadlessProductCheckSteps.java:15)\n\tat ✽.Given User checks the happy path(CartValidation.feature:4)\n",
  "status": "failed"
});
formatter.scenario({
  "line": 7,
  "name": "Validate modify cart for guest user",
  "description": "",
  "id": "these-test-scenario\u0027s-are-used-to-test-the-cart-api\u0027s-of-hybris;validate-modify-cart-for-guest-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 8,
  "name": "User checks the happy path",
  "keyword": "Given "
});
formatter.match({
  "location": "HeadlessProductCheckSteps.initialize_the_Remove_Cart_test_suite()"
});
formatter.result({
  "duration": 824542,
  "error_message": "java.lang.IllegalArgumentException: parameterValue cannot be null\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:80)\n\tat org.codehaus.groovy.reflection.CachedConstructor.doConstructorInvoke(CachedConstructor.java:74)\n\tat org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrap.callConstructor(ConstructorSite.java:84)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:247)\n\tat com.jayway.restassured.internal.assertion.AssertParameter.notNull(AssertParameter.groovy:26)\n\tat com.jayway.restassured.internal.assertion.AssertParameter$notNull.callStatic(Unknown Source)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.pathParameter(RequestSpecificationImpl.groovy:454)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:93)\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1210)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1019)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:810)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:48)\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.callCurrent(PogoInterceptableSite.java:58)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:174)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.pathParam(RequestSpecificationImpl.groovy:472)\n\tat com.levi.api.headless.CartValidation.checkProduct(CartValidation.java:150)\n\tat com.levi.api.headless.steps.HeadlessProductCheckSteps.initialize_the_Remove_Cart_test_suite(HeadlessProductCheckSteps.java:15)\n\tat ✽.Given User checks the happy path(CartValidation.feature:8)\n",
  "status": "failed"
});
formatter.scenario({
  "line": 11,
  "name": "Validate add an item to cart for registered user",
  "description": "",
  "id": "these-test-scenario\u0027s-are-used-to-test-the-cart-api\u0027s-of-hybris;validate-add-an-item-to-cart-for-registered-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 12,
  "name": "User checks the happy path",
  "keyword": "Given "
});
formatter.match({
  "location": "HeadlessProductCheckSteps.initialize_the_Remove_Cart_test_suite()"
});
formatter.result({
  "duration": 719087,
  "error_message": "java.lang.IllegalArgumentException: parameterValue cannot be null\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:80)\n\tat org.codehaus.groovy.reflection.CachedConstructor.doConstructorInvoke(CachedConstructor.java:74)\n\tat org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrap.callConstructor(ConstructorSite.java:84)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:247)\n\tat com.jayway.restassured.internal.assertion.AssertParameter.notNull(AssertParameter.groovy:26)\n\tat com.jayway.restassured.internal.assertion.AssertParameter$notNull.callStatic(Unknown Source)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.pathParameter(RequestSpecificationImpl.groovy:454)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:93)\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1210)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1019)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:810)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:48)\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.callCurrent(PogoInterceptableSite.java:58)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:174)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.pathParam(RequestSpecificationImpl.groovy:472)\n\tat com.levi.api.headless.CartValidation.checkProduct(CartValidation.java:150)\n\tat com.levi.api.headless.steps.HeadlessProductCheckSteps.initialize_the_Remove_Cart_test_suite(HeadlessProductCheckSteps.java:15)\n\tat ✽.Given User checks the happy path(CartValidation.feature:12)\n",
  "status": "failed"
});
formatter.uri("HeadLessProductCheck.feature");
formatter.feature({
  "line": 1,
  "name": "This feature will check the headless paths",
  "description": "",
  "id": "this-feature-will-check-the-headless-paths",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Validate HappyPath to place an order",
  "description": "",
  "id": "this-feature-will-check-the-headless-paths;validate-happypath-to-place-an-order",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "User checks the happy path",
  "keyword": "Given "
});
formatter.match({
  "location": "HeadlessProductCheckSteps.initialize_the_Remove_Cart_test_suite()"
});
formatter.result({
  "duration": 634832,
  "error_message": "java.lang.IllegalArgumentException: parameterValue cannot be null\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)\n\tat sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)\n\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n\tat org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:80)\n\tat org.codehaus.groovy.reflection.CachedConstructor.doConstructorInvoke(CachedConstructor.java:74)\n\tat org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrap.callConstructor(ConstructorSite.java:84)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:247)\n\tat com.jayway.restassured.internal.assertion.AssertParameter.notNull(AssertParameter.groovy:26)\n\tat com.jayway.restassured.internal.assertion.AssertParameter$notNull.callStatic(Unknown Source)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.pathParameter(RequestSpecificationImpl.groovy:454)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.lang.reflect.Method.invoke(Method.java:498)\n\tat org.codehaus.groovy.reflection.CachedMethod.invoke(CachedMethod.java:93)\n\tat groovy.lang.MetaMethod.doMethodInvoke(MetaMethod.java:325)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1210)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:1019)\n\tat groovy.lang.MetaClassImpl.invokeMethod(MetaClassImpl.java:810)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.invokeMethod(RequestSpecificationImpl.groovy)\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.call(PogoInterceptableSite.java:48)\n\tat org.codehaus.groovy.runtime.callsite.PogoInterceptableSite.callCurrent(PogoInterceptableSite.java:58)\n\tat org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:174)\n\tat com.jayway.restassured.internal.RequestSpecificationImpl.pathParam(RequestSpecificationImpl.groovy:472)\n\tat com.levi.api.headless.CartValidation.checkProduct(CartValidation.java:150)\n\tat com.levi.api.headless.steps.HeadlessProductCheckSteps.initialize_the_Remove_Cart_test_suite(HeadlessProductCheckSteps.java:15)\n\tat ✽.Given User checks the happy path(HeadLessProductCheck.feature:4)\n",
  "status": "failed"
});
});