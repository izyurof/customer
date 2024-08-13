package com.iprody.customerservice.specification;

import static com.iprody.customerservice.specifications.CustomerSpecification.filterByCountryName;
import static com.iprody.customerservice.specifications.CustomerSpecification.filterByName;
import static com.iprody.customerservice.specifications.CustomerSpecification.filterBySurname;
import static com.iprody.customerservice.specifications.CustomerSpecification.orderByCountryName;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iprody.customerservice.models.Country;
import com.iprody.customerservice.models.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerSpecificationTests {

    @Mock
    private Root<Customer> root;

    @Mock
    private CriteriaQuery<?> criteriaQuery;

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private Join<Customer, Country> countryJoin;

    @Mock
    private Path<String> namePath;

    @Mock
    private Predicate mockPredicate;

    @Mock
    private Order order;

    @Test
    public void testFilterByNameWhenNameIsNull() {
        // given
        String name = null;

        // when
        when(criteriaBuilder.conjunction()).thenReturn(mockPredicate);
        Predicate result = filterByName(null)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(criteriaBuilder, times(1)).conjunction();
        verify(criteriaBuilder, never()).like(any(Expression.class), any(String.class));
    }

    @Test
    public void testFilterByNameWhenNameIsEmpty() {
        // given
        String name = "";

        // when
        when(criteriaBuilder.conjunction()).thenReturn(mockPredicate);
        Predicate result = filterByName(name)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(criteriaBuilder, times(1)).conjunction();
    }

    @Test
    public void testFilterByNameWhenNameIsValid() {
        // given
        String name = "Jeffrey";

        // when
        when(root.get("name")).thenReturn(mock(Path.class));
        when(criteriaBuilder.like(root.get("name"), name)).thenReturn(mockPredicate);
        Predicate result = filterByName(name)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(criteriaBuilder, times(1))
                .like(root.get("name"), "Jeffrey");
    }

    @Test
    public void testFilterBySurnameWhenSurnameIsNull() {
        // given
        String surname = null;

        // when
        when(criteriaBuilder.conjunction()).thenReturn(mockPredicate);
        Predicate result = filterBySurname(surname)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(criteriaBuilder, times(1)).conjunction();
    }

    @Test
    public void testFilterBySurnameWhenSurnameIsEmpty() {
        // given
        String surname = "";

        // when
        when(criteriaBuilder.conjunction()).thenReturn(mockPredicate);
        Predicate result = filterBySurname(surname)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(criteriaBuilder, times(1)).conjunction();
    }

    @Test
    public void testFilterBySurnameWhenSurnameIsValid() {
        // given
        String surname = "Lebowski";

        // when
        when(root.get("surname")).thenAnswer(invocation -> namePath);
        when(criteriaBuilder.like(root.get("surname"), surname))
                .thenReturn(mockPredicate);
        Predicate result = filterBySurname(surname)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(criteriaBuilder, times(1))
                .like(root.get("surname"), "Lebowski");
    }

    @Test
    public void testFilterByCountryNameWhenCountryNameIsNull() {
        // given
        String countryName = null;

        // when
        when(criteriaBuilder.conjunction()).thenReturn(mockPredicate);
        Predicate result = filterByCountryName(countryName)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(criteriaBuilder, times(1)).conjunction();
    }

    @Test
    public void testFilterByCountryNameWhenCountryNameIsEmpty() {
        // given
        String countryName = "";

        // when
        when(criteriaBuilder.conjunction()).thenReturn(mockPredicate);
        Predicate result = filterByCountryName(countryName)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(criteriaBuilder, times(1)).conjunction();
    }

    @Test
    public void testFilterByCountryNameWhenCountryNameIsValid() {
        // given
        String countryName = "USA";

        // when
        when(root.join("country")).thenAnswer(invocation -> countryJoin);
        when(countryJoin.get("name")).thenAnswer(invocation -> namePath);
        when(criteriaBuilder.equal(namePath, countryName)).thenReturn(mockPredicate);

        Predicate result = filterByCountryName(countryName)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(root).join("country");
        verify(countryJoin).get("name");
        verify(criteriaBuilder).equal(namePath, countryName);
    }

    @Test
    public void testOrderByCountryNameWhenSortingDirectionIsNull() {
        // given
        String sortDirection = null;

        // when
        when(criteriaBuilder.conjunction()).thenReturn(mockPredicate);
        Predicate result = orderByCountryName(sortDirection)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(criteriaQuery, never()).orderBy(any(Order.class));
    }

    @Test
    public void testOrderByCountryNameWhenSortingDirectionIsEmpty() {
        // given
        String sortDirection = "";

        // when
        when(criteriaBuilder.conjunction()).thenReturn(mockPredicate);
        Predicate result = orderByCountryName(sortDirection)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        assertNotNull(result);
        verify(criteriaQuery, never()).orderBy(any(Order.class));
    }

    @Test
    public void testOrderByCountryNameWhenSortingDirectionIsAscending() {
        // given
        String sortDirection = "asc";

        // when
        when(root.join("country")).thenAnswer(invocation -> countryJoin);
        when(countryJoin.get("name")).thenAnswer(invocation -> namePath);
        when(criteriaBuilder.asc(namePath)).thenReturn(order);

        Predicate result = orderByCountryName(sortDirection)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        verify(root).join("country");
        verify(countryJoin).get("name");
        verify(criteriaBuilder).asc(namePath);
        verify(criteriaQuery).orderBy(order);
    }

    @Test
    public void testOrderByCountryNameWhenSortingDirectionIsDescending() {
        // given
        String sortDirection = "desc";

        // when
        when(root.join("country")).thenAnswer(invocation -> countryJoin);
        when(countryJoin.get("name")).thenAnswer(invocation -> namePath);
        when(criteriaBuilder.desc(namePath)).thenReturn(order);

        Predicate result = orderByCountryName(sortDirection)
                .toPredicate(root, criteriaQuery, criteriaBuilder);

        // then
        verify(root).join("country");
        verify(countryJoin).get("name");
        verify(criteriaBuilder).desc(namePath);
        verify(criteriaQuery).orderBy(order);
    }
}
