# Truth, Equality, and JavaScript

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://javascriptweblog.wordpress.com/2011/02/07/truth-equality-and-javascript/](https://javascriptweblog.wordpress.com/2011/02/07/truth-equality-and-javascript/)  
**Captured:** 2026-09-24

## Notes

The article distinguishes strict equality (`===`), which compares without general type coercion, from loose equality (`==`), which applies a set of conversion rules. Those rules can invoke primitive conversion for objects and produce results that surprise readers. The practical default is strict equality unless coercion is intentional and clearly understood.

For truthiness, JavaScript converts values in boolean contexts: `false`, `0`, `-0`, `0n`, `NaN`, `null`, `undefined`, and the empty string are falsy; most other values, including empty arrays and objects, are truthy.

## Connected vault material

- [[Topics/Resources/cosmos/guides/coding_style/javascript/Javascript Guide.md]]
