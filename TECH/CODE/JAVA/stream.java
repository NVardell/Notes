List<String> products = currentMember.getProducts()
        .stream()
        .map(CardProduct::getType)
        .toList();


CardMember currentMember = cardProductsResResponse.getFamilyMembers()
                    .stream()
                    .filter(CardMember::getIsLoggedInUser)
                    .collect(CollectorUtil.toSingleton());