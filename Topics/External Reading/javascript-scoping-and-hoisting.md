# JavaScript Scoping and Hoisting

> Locally authored study notes based on the linked reference. They preserve the concepts needed for offline review; see the original page for the full article and its context.

**Reference:** [https://adequatelygood.com/JavaScript-Scoping-and-Hoisting.html](https://adequatelygood.com/JavaScript-Scoping-and-Hoisting.html)  
**Captured:** 2026-09-24

## Notes

The article explains why a `var` declaration is available throughout its containing function even when written inside a conditional. The declaration is hoisted; its assignment stays at the written location. A function declaration can also affect name resolution before its textual position, which explains examples that otherwise look like assignment to an outer variable.

Modern JavaScript adds block-scoped `let` and `const`; prefer them when block scope is intended. This 2010 article is useful for understanding legacy `var` behavior, not as a complete description of current JavaScript.

## Connected vault material

- [[Topics/Resources/cosmos/guides/coding_style/javascript/Javascript Guide.md]]
