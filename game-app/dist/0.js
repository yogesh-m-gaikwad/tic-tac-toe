(window["webpackJsonp"] = window["webpackJsonp"] || []).push([[0],{

/***/ "./node_modules/web-vitals/dist/web-vitals.es5.min.js":
/*!************************************************************!*\
  !*** ./node_modules/web-vitals/dist/web-vitals.es5.min.js ***!
  \************************************************************/
/*! exports provided: getCLS, getFCP, getFID, getLCP, getTTFB */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getCLS", function() { return p; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getFCP", function() { return v; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getFID", function() { return f; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getLCP", function() { return g; });
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "getTTFB", function() { return h; });
var t,
    n,
    e = function () {
  return "".concat(Date.now(), "-").concat(Math.floor(8999999999999 * Math.random()) + 1e12);
},
    i = function (t) {
  var n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : -1;
  return {
    name: t,
    value: n,
    delta: 0,
    entries: [],
    id: e(),
    isFinal: !1
  };
},
    a = function (t, n) {
  try {
    if (PerformanceObserver.supportedEntryTypes.includes(t)) {
      var e = new PerformanceObserver(function (t) {
        return t.getEntries().map(n);
      });
      return e.observe({
        type: t,
        buffered: !0
      }), e;
    }
  } catch (t) {}
},
    r = !1,
    o = !1,
    s = function (t) {
  r = !t.persisted;
},
    u = function () {
  addEventListener("pagehide", s), addEventListener("beforeunload", function () {});
},
    c = function (t) {
  var n = arguments.length > 1 && void 0 !== arguments[1] && arguments[1];
  o || (u(), o = !0), addEventListener("visibilitychange", function (n) {
    var e = n.timeStamp;
    "hidden" === document.visibilityState && t({
      timeStamp: e,
      isUnloading: r
    });
  }, {
    capture: !0,
    once: n
  });
},
    l = function (t, n, e, i) {
  var a;
  return function () {
    e && n.isFinal && e.disconnect(), n.value >= 0 && (i || n.isFinal || "hidden" === document.visibilityState) && (n.delta = n.value - (a || 0), (n.delta || n.isFinal || void 0 === a) && (t(n), a = n.value));
  };
},
    p = function (t) {
  var n,
      e = arguments.length > 1 && void 0 !== arguments[1] && arguments[1],
      r = i("CLS", 0),
      o = function (t) {
    t.hadRecentInput || (r.value += t.value, r.entries.push(t), n());
  },
      s = a("layout-shift", o);

  s && (n = l(t, r, s, e), c(function (t) {
    var e = t.isUnloading;
    s.takeRecords().map(o), e && (r.isFinal = !0), n();
  }));
},
    d = function () {
  return void 0 === t && (t = "hidden" === document.visibilityState ? 0 : 1 / 0, c(function (n) {
    var e = n.timeStamp;
    return t = e;
  }, !0)), {
    get timeStamp() {
      return t;
    }

  };
},
    v = function (t) {
  var n,
      e = i("FCP"),
      r = d(),
      o = a("paint", function (t) {
    "first-contentful-paint" === t.name && t.startTime < r.timeStamp && (e.value = t.startTime, e.isFinal = !0, e.entries.push(t), n());
  });
  o && (n = l(t, e, o));
},
    f = function (t) {
  var n = i("FID"),
      e = d(),
      r = function (t) {
    t.startTime < e.timeStamp && (n.value = t.processingStart - t.startTime, n.entries.push(t), n.isFinal = !0, s());
  },
      o = a("first-input", r),
      s = l(t, n, o);

  o ? c(function () {
    o.takeRecords().map(r), o.disconnect();
  }, !0) : window.perfMetrics && window.perfMetrics.onFirstInputDelay && window.perfMetrics.onFirstInputDelay(function (t, i) {
    i.timeStamp < e.timeStamp && (n.value = t, n.isFinal = !0, n.entries = [{
      entryType: "first-input",
      name: i.type,
      target: i.target,
      cancelable: i.cancelable,
      startTime: i.timeStamp,
      processingStart: i.timeStamp + t
    }], s());
  });
},
    m = function () {
  return n || (n = new Promise(function (t) {
    return ["scroll", "keydown", "pointerdown"].map(function (n) {
      addEventListener(n, t, {
        once: !0,
        passive: !0,
        capture: !0
      });
    });
  })), n;
},
    g = function (t) {
  var n,
      e = arguments.length > 1 && void 0 !== arguments[1] && arguments[1],
      r = i("LCP"),
      o = d(),
      s = function (t) {
    var e = t.startTime;
    e < o.timeStamp ? (r.value = e, r.entries.push(t)) : r.isFinal = !0, n();
  },
      u = a("largest-contentful-paint", s);

  if (u) {
    n = l(t, r, u, e);

    var p = function () {
      r.isFinal || (u.takeRecords().map(s), r.isFinal = !0, n());
    };

    m().then(p), c(p, !0);
  }
},
    h = function (t) {
  var n,
      e = i("TTFB");
  n = function () {
    try {
      var n = performance.getEntriesByType("navigation")[0] || function () {
        var t = performance.timing,
            n = {
          entryType: "navigation",
          startTime: 0
        };

        for (var e in t) "navigationStart" !== e && "toJSON" !== e && (n[e] = Math.max(t[e] - t.navigationStart, 0));

        return n;
      }();

      e.value = e.delta = n.responseStart, e.entries = [n], e.isFinal = !0, t(e);
    } catch (t) {}
  }, "complete" === document.readyState ? setTimeout(n, 0) : addEventListener("pageshow", n);
};



/***/ })

}]);
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbIndlYnBhY2s6Ly8vLi9ub2RlX21vZHVsZXMvd2ViLXZpdGFscy9kaXN0L3dlYi12aXRhbHMuZXM1Lm1pbi5qcyJdLCJuYW1lcyI6WyJ0IiwibiIsImUiLCJjb25jYXQiLCJEYXRlIiwibm93IiwiTWF0aCIsImZsb29yIiwicmFuZG9tIiwiaSIsImFyZ3VtZW50cyIsImxlbmd0aCIsIm5hbWUiLCJ2YWx1ZSIsImRlbHRhIiwiZW50cmllcyIsImlkIiwiaXNGaW5hbCIsImEiLCJQZXJmb3JtYW5jZU9ic2VydmVyIiwic3VwcG9ydGVkRW50cnlUeXBlcyIsImluY2x1ZGVzIiwiZ2V0RW50cmllcyIsIm1hcCIsIm9ic2VydmUiLCJ0eXBlIiwiYnVmZmVyZWQiLCJyIiwibyIsInMiLCJwZXJzaXN0ZWQiLCJ1IiwiYWRkRXZlbnRMaXN0ZW5lciIsImMiLCJ0aW1lU3RhbXAiLCJkb2N1bWVudCIsInZpc2liaWxpdHlTdGF0ZSIsImlzVW5sb2FkaW5nIiwiY2FwdHVyZSIsIm9uY2UiLCJsIiwiZGlzY29ubmVjdCIsInAiLCJoYWRSZWNlbnRJbnB1dCIsInB1c2giLCJ0YWtlUmVjb3JkcyIsImQiLCJ2Iiwic3RhcnRUaW1lIiwiZiIsInByb2Nlc3NpbmdTdGFydCIsIndpbmRvdyIsInBlcmZNZXRyaWNzIiwib25GaXJzdElucHV0RGVsYXkiLCJlbnRyeVR5cGUiLCJ0YXJnZXQiLCJjYW5jZWxhYmxlIiwibSIsIlByb21pc2UiLCJwYXNzaXZlIiwiZyIsInRoZW4iLCJoIiwicGVyZm9ybWFuY2UiLCJnZXRFbnRyaWVzQnlUeXBlIiwidGltaW5nIiwibWF4IiwibmF2aWdhdGlvblN0YXJ0IiwicmVzcG9uc2VTdGFydCIsInJlYWR5U3RhdGUiLCJzZXRUaW1lb3V0Il0sIm1hcHBpbmdzIjoiOzs7Ozs7Ozs7O0FBQUE7QUFBQTtBQUFBO0FBQUE7QUFBQTtBQUFBO0FBQUEsSUFBSUEsQ0FBSjtBQUFBLElBQU1DLENBQU47QUFBQSxJQUFRQyxDQUFDLEdBQUMsWUFBVTtBQUFD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QUFGLEdBQVUsQ0FBQyxDQUF6RCxFQUEyRGpCLENBQUMsQ0FBQ0UsQ0FBRCxDQUE1RDtBQUFnRSxLQUE5UyxDQUE4UyxPQUFNRixDQUFOLEVBQVEsQ0FBRTtBQUFDLEdBQXRVLEVBQXVVLGVBQWFtQyxRQUFRLENBQUNrQyxVQUF0QixHQUFpQ0MsVUFBVSxDQUFDckUsQ0FBRCxFQUFHLENBQUgsQ0FBM0MsR0FBaUQrQixnQkFBZ0IsQ0FBQyxVQUFELEVBQVkvQixDQUFaLENBQXhZO0FBQXVaLENBQXpuRyIsImZpbGUiOiIwLmpzIiwic291cmNlc0NvbnRlbnQiOlsidmFyIHQsbixlPWZ1bmN0aW9uKCl7cmV0dXJuXCJcIi5jb25jYXQoRGF0ZS5ub3coKSxcIi1cIikuY29uY2F0KE1hdGguZmxvb3IoODk5OTk5OTk5OTk5OSpNYXRoLnJhbmRvbSgpKSsxZTEyKX0saT1mdW5jdGlvbih0KXt2YXIgbj1hcmd1bWVudHMubGVuZ3RoPjEmJnZvaWQgMCE9PWFyZ3VtZW50c1sxXT9hcmd1bWVudHNbMV06LTE7cmV0dXJue25hbWU6dCx2YWx1ZTpuLGRlbHRhOjAsZW50cmllczpbXSxpZDplKCksaXNGaW5hbDohMX19LGE9ZnVuY3Rpb24odCxuKXt0cnl7aWYoUGVyZm9ybWFuY2VPYnNlcnZlci5zdXBwb3J0ZWRFbnRyeVR5cGVzLmluY2x1ZGVzKHQpKXt2YXIgZT1uZXcgUGVyZm9ybWFuY2VPYnNlcnZlcigoZnVuY3Rpb24odCl7cmV0dXJuIHQuZ2V0RW50cmllcygpLm1hcChuKX0pKTtyZXR1cm4gZS5vYnNlcnZlKHt0eXBlOnQsYnVmZmVyZWQ6ITB9KSxlfX1jYXRjaCh0KXt9fSxyPSExLG89ITEscz1mdW5jdGlvbih0KXtyPSF0LnBlcnNpc3RlZH0sdT1mdW5jdGlvbigpe2FkZEV2ZW50TGlzdGVuZXIoXCJwYWdlaGlkZVwiLHMpLGFkZEV2ZW50TGlzdGVuZXIoXCJiZWZvcmV1bmxvYWRcIiwoZnVuY3Rpb24oKXt9KSl9LGM9ZnVuY3Rpb24odCl7dmFyIG49YXJndW1lbnRzLmxlbmd0aD4xJiZ2b2lkIDAhPT1hcmd1bWVudHNbMV0mJmFyZ3VtZW50c1sxXTtvfHwodSgpLG89ITApLGFkZEV2ZW50TGlzdGVuZXIoXCJ2aXNpYmlsaXR5Y2hhbmdlXCIsKGZ1bmN0aW9uKG4pe3ZhciBlPW4udGltZVN0YW1wO1wiaGlkZGVuXCI9PT1kb2N1bWVudC52aXNpYmlsaXR5U3RhdGUmJnQoe3RpbWVTdGFtcDplLGlzVW5sb2FkaW5nOnJ9KX0pLHtjYXB0dXJlOiEwLG9uY2U6bn0pfSxsPWZ1bmN0aW9uKHQsbixlLGkpe3ZhciBhO3JldHVybiBmdW5jdGlvbigpe2UmJm4uaXNGaW5hbCYmZS5kaXNjb25uZWN0KCksbi52YWx1ZT49MCYmKGl8fG4uaXNGaW5hbHx8XCJoaWRkZW5cIj09PWRvY3VtZW50LnZpc2liaWxpdHlTdGF0ZSkmJihuLmRlbHRhPW4udmFsdWUtKGF8fDApLChuLmRlbHRhfHxuLmlzRmluYWx8fHZvaWQgMD09PWEpJiYodChuKSxhPW4udmFsdWUpKX19LHA9ZnVuY3Rpb24odCl7dmFyIG4sZT1hcmd1bWVudHMubGVuZ3RoPjEmJnZvaWQgMCE9PWFyZ3VtZW50c1sxXSYmYXJndW1lbnRzWzFdLHI9aShcIkNMU1wiLDApLG89ZnVuY3Rpb24odCl7dC5oYWRSZWNlbnRJbnB1dHx8KHIudmFsdWUrPXQudmFsdWUsci5lbnRyaWVzLnB1c2godCksbigpKX0scz1hKFwibGF5b3V0LXNoaWZ0XCIsbyk7cyYmKG49bCh0LHIscyxlKSxjKChmdW5jdGlvbih0KXt2YXIgZT10LmlzVW5sb2FkaW5nO3MudGFrZVJlY29yZHMoKS5tYXAobyksZSYmKHIuaXNGaW5hbD0hMCksbigpfSkpKX0sZD1mdW5jdGlvbigpe3JldHVybiB2b2lkIDA9PT10JiYodD1cImhpZGRlblwiPT09ZG9jdW1lbnQudmlzaWJpbGl0eVN0YXRlPzA6MS8wLGMoKGZ1bmN0aW9uKG4pe3ZhciBlPW4udGltZVN0YW1wO3JldHVybiB0PWV9KSwhMCkpLHtnZXQgdGltZVN0YW1wKCl7cmV0dXJuIHR9fX0sdj1mdW5jdGlvbih0KXt2YXIgbixlPWkoXCJGQ1BcIikscj1kKCksbz1hKFwicGFpbnRcIiwoZnVuY3Rpb24odCl7XCJmaXJzdC1jb250ZW50ZnVsLXBhaW50XCI9PT10Lm5hbWUmJnQuc3RhcnRUaW1lPHIudGltZVN0YW1wJiYoZS52YWx1ZT10LnN0YXJ0VGltZSxlLmlzRmluYWw9ITAsZS5lbnRyaWVzLnB1c2godCksbigpKX0pKTtvJiYobj1sKHQsZSxvKSl9LGY9ZnVuY3Rpb24odCl7dmFyIG49aShcIkZJRFwiKSxlPWQoKSxyPWZ1bmN0aW9uKHQpe3Quc3RhcnRUaW1lPGUudGltZVN0YW1wJiYobi52YWx1ZT10LnByb2Nlc3NpbmdTdGFydC10LnN0YXJ0VGltZSxuLmVudHJpZXMucHVzaCh0KSxuLmlzRmluYWw9ITAscygpKX0sbz1hKFwiZmlyc3QtaW5wdXRcIixyKSxzPWwodCxuLG8pO28/YygoZnVuY3Rpb24oKXtvLnRha2VSZWNvcmRzKCkubWFwKHIpLG8uZGlzY29ubmVjdCgpfSksITApOndpbmRvdy5wZXJmTWV0cmljcyYmd2luZG93LnBlcmZNZXRyaWNzLm9uRmlyc3RJbnB1dERlbGF5JiZ3aW5kb3cucGVyZk1ldHJpY3Mub25GaXJzdElucHV0RGVsYXkoKGZ1bmN0aW9uKHQsaSl7aS50aW1lU3RhbXA8ZS50aW1lU3RhbXAmJihuLnZhbHVlPXQsbi5pc0ZpbmFsPSEwLG4uZW50cmllcz1be2VudHJ5VHlwZTpcImZpcnN0LWlucHV0XCIsbmFtZTppLnR5cGUsdGFyZ2V0OmkudGFyZ2V0LGNhbmNlbGFibGU6aS5jYW5jZWxhYmxlLHN0YXJ0VGltZTppLnRpbWVTdGFtcCxwcm9jZXNzaW5nU3RhcnQ6aS50aW1lU3RhbXArdH1dLHMoKSl9KSl9LG09ZnVuY3Rpb24oKXtyZXR1cm4gbnx8KG49bmV3IFByb21pc2UoKGZ1bmN0aW9uKHQpe3JldHVybltcInNjcm9sbFwiLFwia2V5ZG93blwiLFwicG9pbnRlcmRvd25cIl0ubWFwKChmdW5jdGlvbihuKXthZGRFdmVudExpc3RlbmVyKG4sdCx7b25jZTohMCxwYXNzaXZlOiEwLGNhcHR1cmU6ITB9KX0pKX0pKSksbn0sZz1mdW5jdGlvbih0KXt2YXIgbixlPWFyZ3VtZW50cy5sZW5ndGg+MSYmdm9pZCAwIT09YXJndW1lbnRzWzFdJiZhcmd1bWVudHNbMV0scj1pKFwiTENQXCIpLG89ZCgpLHM9ZnVuY3Rpb24odCl7dmFyIGU9dC5zdGFydFRpbWU7ZTxvLnRpbWVTdGFtcD8oci52YWx1ZT1lLHIuZW50cmllcy5wdXNoKHQpKTpyLmlzRmluYWw9ITAsbigpfSx1PWEoXCJsYXJnZXN0LWNvbnRlbnRmdWwtcGFpbnRcIixzKTtpZih1KXtuPWwodCxyLHUsZSk7dmFyIHA9ZnVuY3Rpb24oKXtyLmlzRmluYWx8fCh1LnRha2VSZWNvcmRzKCkubWFwKHMpLHIuaXNGaW5hbD0hMCxuKCkpfTttKCkudGhlbihwKSxjKHAsITApfX0saD1mdW5jdGlvbih0KXt2YXIgbixlPWkoXCJUVEZCXCIpO249ZnVuY3Rpb24oKXt0cnl7dmFyIG49cGVyZm9ybWFuY2UuZ2V0RW50cmllc0J5VHlwZShcIm5hdmlnYXRpb25cIilbMF18fGZ1bmN0aW9uKCl7dmFyIHQ9cGVyZm9ybWFuY2UudGltaW5nLG49e2VudHJ5VHlwZTpcIm5hdmlnYXRpb25cIixzdGFydFRpbWU6MH07Zm9yKHZhciBlIGluIHQpXCJuYXZpZ2F0aW9uU3RhcnRcIiE9PWUmJlwidG9KU09OXCIhPT1lJiYobltlXT1NYXRoLm1heCh0W2VdLXQubmF2aWdhdGlvblN0YXJ0LDApKTtyZXR1cm4gbn0oKTtlLnZhbHVlPWUuZGVsdGE9bi5yZXNwb25zZVN0YXJ0LGUuZW50cmllcz1bbl0sZS5pc0ZpbmFsPSEwLHQoZSl9Y2F0Y2godCl7fX0sXCJjb21wbGV0ZVwiPT09ZG9jdW1lbnQucmVhZHlTdGF0ZT9zZXRUaW1lb3V0KG4sMCk6YWRkRXZlbnRMaXN0ZW5lcihcInBhZ2VzaG93XCIsbil9O2V4cG9ydHtwIGFzIGdldENMUyx2IGFzIGdldEZDUCxmIGFzIGdldEZJRCxnIGFzIGdldExDUCxoIGFzIGdldFRURkJ9O1xuIl0sInNvdXJjZVJvb3QiOiIifQ==