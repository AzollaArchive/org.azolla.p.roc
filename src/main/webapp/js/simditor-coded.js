(function (root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD. Register as an anonymous module unless amdModuleId is set
    define('simditor-coded', ["jquery", "simditor"], function (a0, b1) {
      return (root['SimditorCoded'] = factory(a0, b1));
    });
  } else if (typeof exports === 'object') {
    // Node. Does not work with strict CommonJS, but
    // only CommonJS-like environments that support module.exports,
    // like Node.
    module.exports = factory(require("jquery"), require("simditor"));
  } else {
    root['SimditorCoded'] = factory(jQuery, Simditor);
  }
}(this, function ($, Simditor) {

  var SimditorCoded,
    extend = function (child, parent) {
      for (var key in parent) {
        if (hasProp.call(parent, key)) child[key] = parent[key];
      }
      function ctor() {
        this.constructor = child;
      }

      ctor.prototype = parent.prototype;
      child.prototype = new ctor();
      child.__super__ = parent.prototype;
      return child;
    },
    hasProp = {}.hasOwnProperty;

  SimditorCoded = (function (superClass) {
    extend(SimditorCoded, superClass);

    function SimditorCoded() {
      return SimditorCoded.__super__.constructor.apply(this, arguments);
    }

    SimditorCoded.prototype.name = 'coded';

    SimditorCoded.prototype.icon = 'coded';//times,chain-broken,caret-down,caret-right,undo,font-minus

    SimditorCoded.prototype.htmlTag = 'code';

    SimditorCoded.prototype.disableTag = 'li, table';

    SimditorCoded.prototype.command = function () {
      var $end, $start, range;
      range = this.editor.selection.getRange();
      if (this.active) {
        this.editor.selection.save();
        this.uncoded(range);
        this.editor.selection.restore();
        this.editor.trigger('valuechanged');
        return;
      }
      if (range.collapsed) {
        return;
      }
      this.editor.selection.save();
      $start = $(range.startContainer);
      $end = $(range.endContainer);
      if ($start.closest('code').length) {
        range.setStartBefore($start.closest('code')[0]);
      }
      if ($end.closest('code').length) {
        range.setEndAfter($end.closest('code')[0]);
      }
      this.coded(range);
      this.editor.selection.restore();
      this.editor.trigger('valuechanged');
      if (this.editor.util.support.onselectionchange) {
        return this.editor.trigger('selectionchanged');
      }
    };

    SimditorCoded.prototype.coded = function (range) {
      var $contents, $coded;
      if (range == null) {
        range = this.editor.selection.range();
      }
      $contents = $(range.extractContents());
      $contents.find('code').each(function (index, ele) {
        return $(ele).replaceWith($(ele).html());
      });
      $coded = $('<code>').append($contents);
      return range.insertNode($coded[0]);
    };

    SimditorCoded.prototype.uncoded = function (range) {
      var $coded;
      if (range == null) {
        range = this.editor.selection.range();
      }
      if (range.collapsed) {
        $coded = $(range.commonAncestorContainer);
        if (!$coded.is('code')) {
          $coded = $coded.parent();
        }
      } else if ($(range.startContainer).closest('code').length) {
        $coded = $(range.startContainer).closest('code');
      } else if ($(range.endContainer).closest('code').length) {
        $coded = $(range.endContainer).closest('code');
      }
      return $coded.replaceWith($coded.html());
    };

    return SimditorCoded;

  })(Simditor.Button);

  Simditor.Toolbar.addButton(SimditorCoded);

  return SimditorCoded;

}));
